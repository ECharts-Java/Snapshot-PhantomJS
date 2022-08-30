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

public class SnapshotTest {
    private Option option;

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

        this.option = option;
    }

    @Test
    public void testCheckPhantomJSFailure() {

    }

    @Test
    public void testTakeSnapshot() {
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "./", "x.png");
        // builder.getOption();
        System.out.println(Snapshot.takeSnapshot(builder));
    }
}