package org.icepear.echarts;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.icepear.echarts.render.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snapshot {
    private static final String PHANTOMJS_EXEC = "phantomjs";
    private static final String TEMP_PATH = "test.html";
    private static final String SCRIPT_NAME = "generate-images.js";
    private static Logger logger = LoggerFactory.getLogger(Snapshot.class); 

    private static void writeHtml(String html, String path, Boolean willOpen) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(html);
            writer.close();
            if (willOpen) {
                File file = new File(path);
                Desktop.getDesktop().browse(file.toURI());
            }
        } catch (IOException e) {
            logger.error("Write Html failed. Path error.");
        }
    }

    public static void checkPhantomJS() {
        try {
            Process p = new ProcessBuilder(PHANTOMJS_EXEC, "--version").start();
            String stdout = IOUtils.toString(p.getInputStream(), Charset.defaultCharset());
            logger.info("PhantomJS is installed and its version is" + stdout);
        } catch (Exception e) {
            logger.error("PhantomJS is not installed. You need to install it before proceeding.");
            System.exit(-1);
        }
    }

    public static String takeSnapshot(SnapshotSettingsBuilder settings) {
        // checkPhantomJS();
        logger.info("Generating files...");
        Option option = settings.getOption();
        Chart<?, ?> chart = settings.getChart();
        Engine engine = new Engine();
        String content = "";

        
        String html = (option == null) ? engine.renderHtml(chart) : engine.renderHtml(option);
        writeHtml(html, TEMP_PATH, false);
        
        try {
            URL res = Snapshot.class.getClassLoader().getResource(SCRIPT_NAME);
            String scriptPath = res.getPath();
            System.out.println(scriptPath);
            Process p = new ProcessBuilder(PHANTOMJS_EXEC, scriptPath, settings.getPath(), settings.getFileType(), "2000", "2").start();
            content = IOUtils.toString(p.getInputStream(), Charset.defaultCharset());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return content;
    }
}