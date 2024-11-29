package org.icepear.echarts.utils;
import java.util.Base64;

public class Utils {
    /**
     *
     * @param data
     * @return decoded bytes from base64 data
     */
    public static byte[] base64Decode(String data) {
        return Base64.getMimeDecoder().decode(data);
    }
}
