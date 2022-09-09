package org.icepear.echarts;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SnapshotTestWithEmptyChart {
    private Option option = null;

    @Test
    public void testTakeSnapshot() {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
        assertEquals("", Snapshot.takeSnapshot(builder));
    }

}
