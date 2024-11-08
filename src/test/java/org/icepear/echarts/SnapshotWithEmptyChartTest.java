package org.icepear.echarts;

import org.icepear.echarts.exceptions.UnsupportedConfigurationException;
import org.junit.Test;

public class SnapshotWithEmptyChartTest {
    private Option option = null;

    @Test(expected = UnsupportedConfigurationException.class)
    public void testTakeSnapshotThrowUnspportedChart() throws Exception{
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
        Snapshot.takeSnapshot(builder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidFileTypeShouldThrowException() throws Exception {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "xxx");
        Snapshot.takeSnapshot(builder);
    }
}
