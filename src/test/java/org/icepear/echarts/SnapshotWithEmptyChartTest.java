package org.icepear.echarts;

import org.icepear.echarts.exceptions.UnsupportedConfigurationException;
import org.junit.Test;

public class SnapshotWithEmptyChartTest {
    private Option option = null;

    @Test(expected = UnsupportedConfigurationException.class)
    public void testTakeSnapshot() throws Exception{
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
        Snapshot.takeSnapshot(builder);
    }
}
