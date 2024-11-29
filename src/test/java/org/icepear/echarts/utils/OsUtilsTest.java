package org.icepear.echarts.utils;

import org.junit.Test;

public class OsUtilsTest {
    
    // TODO - introduce mock
    @Test
    public void testIsWindows() {
        System.out.println(OsUtils.getOsName());
        System.out.println(OsUtils.isWindows());
    }
}
