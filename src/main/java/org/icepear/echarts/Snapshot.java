package org.icepear.echarts;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.icepear.echarts.render.Engine;
import org.icepear.echarts.snapshotSaver.Base64Saver;
import org.icepear.echarts.snapshotSaver.ImageSaver;
import org.icepear.echarts.snapshotSaver.SnapShotSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snapshot {
    private static final String PHANTOMJS_EXEC = "phantomjs";
    private static final String SCRIPT_NAME = "generate-images.js";
    private static final String[] SUPPORTED_FILE_TYPES = new String[] {"png", "jpg"};
    private static Logger logger = LoggerFactory.getLogger(Snapshot.class);

    private static void writeStdin(String html, OutputStream stdin) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
            writer.write(html);
            writer.close();
        } catch (IOException e) {
            logger.error("Write Html into STDIN failed. " + e.getMessage());
        }
    }

    public static boolean checkPhantomJS() {
        try {
            Process p = new ProcessBuilder(PHANTOMJS_EXEC, "--version").start();
            String stdout = IOUtils.toString(p.getInputStream(), Charset.defaultCharset());
            logger.info("PhantomJS is installed and its version is " + stdout);
        } catch (Exception e) {
            logger.error("PhantomJS is not installed. You need to install it before proceeding.");
            return false;
        }
        return true;
    }

    private static boolean isFileTypeSupported(String fileType) {
        for(String supportedType : SUPPORTED_FILE_TYPES) {
            if(supportedType.equals(fileType)) return true;
        }
        return false;
    }

    public static String takeSnapshot(SnapshotSettingsBuilder settings) {
        if(!isFileTypeSupported(settings.getFileType())) {
            logger.error("The file type you request is not supported.");
            return "";
        }
        if(!checkPhantomJS()){
            return "";
        }
        logger.info("Generating files...");
        Option option = settings.getOption();
        Chart<?, ?> chart = settings.getChart();
        Engine engine = new Engine();
        String content = "";

        String html = (option == null) ? engine.renderHtml(chart) : engine.renderHtml(option);

        try {
            URL res = Snapshot.class.getClassLoader().getResource(SCRIPT_NAME);
            String scriptPath = res.getPath();
            Process p = new ProcessBuilder(PHANTOMJS_EXEC, scriptPath, settings.getFileType(),
                    settings.getDelay() * 1000 + "", settings.getPixelRatio() + "").start();
            writeStdin(html, p.getOutputStream());
            content = IOUtils.toString(p.getInputStream(), Charset.defaultCharset());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return content;
    }

    public static void saveSnapShot(String imageData, String path) {
        String[] pathParts = path.split("\\.");
        String suffix = pathParts[pathParts.length-1];
        SnapShotSaver saver;
        if(suffix.equals("png") || suffix.equals("jpg")) {
            saver = new ImageSaver();
        } else {
            saver = new Base64Saver();
        }
        saver.save(imageData, path);
    }
}