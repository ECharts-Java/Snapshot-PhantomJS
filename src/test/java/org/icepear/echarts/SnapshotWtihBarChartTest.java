package org.icepear.echarts;

import org.junit.Before;
import org.junit.Test;

public class SnapshotWtihBarChartTest {
    private Option option;
    private String height = "600px";
    private String width = "600px";

    @Before
    public void buildOption() {
        Bar bar = new Bar()
                .setLegend()
                .setTooltip("item")
                .addXAxis(new String[] { "Matcha Latte", "Milk Tea", "Cheese Cocoa", "Walnut Brownie" })
                .addYAxis()
                .addSeries("2015", new Number[] { 43.3, 83.1, 86.4, 72.4 })
                .addSeries("2016", new Number[] { 85.8, 73.4, 65.2, 53.9 })
                .addSeries("2017", new Number[] { 93.7, 55.1, 82.5, 39.1 });
        option = bar.getOption().setAnimation(false);
    }

    @Test
    public void testSaveSnapshotBase64() {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.txt");
    }

    @Test
    public void testSaveSnapshotPNG() {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png", height, width, 1);
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.png");
    }

    @Test
    public void testSaveSnapshotJPG() {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "jpg", height, width, 1);
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.jpg");
    }

}
