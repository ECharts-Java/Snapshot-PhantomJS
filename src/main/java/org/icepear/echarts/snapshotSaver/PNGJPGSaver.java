package org.icepear.echarts.snapshotSaver;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.icepear.echarts.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PNGJPGSaver implements SnapShotSaver{

    private static Logger logger = LoggerFactory.getLogger(PNGJPGSaver.class);

    public void save(String imageData, String outputPath) {
        String[] contentArray = imageData.split(",");
        if(contentArray.length != 2) {
            logger.error("Illegal imagedata.");            
            return;
        }
        System.out.println(contentArray[1]);
        byte[] imageBytes = Utils.base64Decode(contentArray[1]);
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
