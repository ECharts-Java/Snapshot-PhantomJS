package org.icepear.echarts.snapshotSaver;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.icepear.echarts.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageSaver implements SnapShotSaver{

    private static Logger logger = LoggerFactory.getLogger(ImageSaver.class);
    
    public void save(String imageData, String outputPath) {
        byte[] imageBytes = Utils.base64Decode(imageData);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputPath));
            bos.write(imageBytes);
            bos.flush();
            bos.close();
        } catch(IOException e) {
            logger.error("Save image to PNG/JPG file failed. " + e.getMessage());
        }
    }
}
