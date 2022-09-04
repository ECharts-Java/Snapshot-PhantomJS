package org.icepear.echarts;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.apache.commons.io.IOUtils;
import org.icepear.echarts.render.Engine;
import org.icepear.echarts.snapshotSaver.SnapShotSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snapshot {
    private static final String PHANTOMJS_EXEC = "phantomjs";
    private static final String TEMP_PATH = "test.html";
    private static final String SCRIPT_NAME = "generate-images.js";
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

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static String takeSnapshot(SnapshotSettingsBuilder settings) {
        // checkPhantomJS();
        logger.info("Generating files...");
        Option option = settings.getOption();
        Chart<?, ?> chart = settings.getChart();
        Engine engine = new Engine();
        String content = "";

        // String html = (option == null) ? engine.renderHtml(chart) : engine.renderHtml(option);

        try {
            InputStream htmlStream = Snapshot.class.getResourceAsStream("/test.html");
            String html = readFromInputStream(htmlStream);
            System.out.println(html);
            
            URL res = Snapshot.class.getClassLoader().getResource(SCRIPT_NAME);
            String scriptPath = res.getPath();
            System.out.println(scriptPath);
            Process p = new ProcessBuilder(PHANTOMJS_EXEC, scriptPath, settings.getPath(), settings.getFileType(),
                    "2000", "2").start();
            writeStdin(html, p.getOutputStream());
            content = IOUtils.toString(p.getInputStream(), Charset.defaultCharset());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        // System.out.println(content);
        return content;
    }

    public static void saveSnapShot(String imageData, String path, SnapShotSaver snapshotSaver) {
        snapshotSaver.save(imageData, path);
    }
}