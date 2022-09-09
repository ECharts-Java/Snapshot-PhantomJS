package org.icepear.echarts.snapshotSaver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Base64Saver implements SnapShotSaver{
    
    private static Logger logger = LoggerFactory.getLogger(Base64Saver.class);
    /**
     * save the image as base64 string or SVG
     */
    public void save(String imageData, String outputPath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
            writer.write(imageData);
            writer.close();
        } catch(IOException e) {
            logger.error("Save image to SVG/Base64 file failed. " + e.getMessage());
        }
    }
}
