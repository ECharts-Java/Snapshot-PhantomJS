package org.icepear.echarts;

import org.icepear.echarts.charts.bar.BarSeries;
import org.icepear.echarts.components.coord.CategoryAxisTick;
import org.icepear.echarts.components.coord.cartesian.CategoryAxis;
import org.icepear.echarts.components.coord.cartesian.ValueAxis;
import org.icepear.echarts.components.grid.Grid;
import org.icepear.echarts.components.tooltip.Tooltip;
import org.icepear.echarts.components.tooltip.TooltipAxisPointer;
import org.icepear.echarts.origin.util.SeriesOption;
import org.junit.Before;
import org.junit.Test;

public class SnapshotWithoutToolBoxTest {
    private Option option;
    private String height = "600px";
    private String width = "600px";

    @Before
    public void buildOption() {
        Tooltip tooltip = new Tooltip()
                .setTrigger("axis")
                .setAxisPointer(new TooltipAxisPointer()
                        .setType("shadow"));

        Grid grid = new Grid()
                .setLeft("3%")
                .setRight("4%")
                .setBottom("3%")
                .setContainLabel(true);

        CategoryAxis xAxis = new CategoryAxis()
                .setType("category")
                .setData(new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" })
                .setAxisTick(new CategoryAxisTick()
                        .setAlignWithLabel(true));

        ValueAxis yAxis = new ValueAxis().setType("value");

        BarSeries series = new BarSeries()
                .setName("Direct")
                .setType("bar")
                .setBarWidth("60%")
                .setData(new Number[] { 10, 52, 200, 334, 390, 330, 220 });

        Option option = new Option()
                .setTooltip(tooltip)
                .setGrid(grid)
                .setXAxis(xAxis)
                .setYAxis(yAxis)
                .setSeries(new SeriesOption[] { series });

        this.option = option.setAnimation(false);
    }

    /**
     * Only used when phantomjs is not installed since we didn't mock the result
     */
    // @Test
    // public void testCheckPhantomJSFailure() {
    // SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
    // assertTrue(!Snapshot.checkPhantomJS());
    // assertEquals("", Snapshot.takeSnapshot(builder));
    // }

    @Test
    public void testTakeSnapshot() throws Exception {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
        System.out.println(Snapshot.takeSnapshot(builder));
    }

    @Test
    public void testSaveSnapshotBase64() throws Exception {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.txt");
    }

    @Test
    public void testSaveSnapshotPNG() throws Exception {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png", height, width, 1);
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.png");
    }

    @Test
    public void testSaveSnapshotJPG() throws Exception {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "jpg", height, width, 1);
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.jpg");
    }
}