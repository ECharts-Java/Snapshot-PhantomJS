package org.icepear.echarts;
import java.util.HashMap;
import org.icepear.echarts.charts.line.LineSeries;
import org.icepear.echarts.components.coord.ValueAxisLabel;
import org.icepear.echarts.components.coord.cartesian.CategoryAxis;
import org.icepear.echarts.components.coord.cartesian.ValueAxis;
import org.icepear.echarts.components.legend.Legend;
import org.icepear.echarts.components.marker.MarkLine;
import org.icepear.echarts.components.marker.MarkLine1DDataItem;
import org.icepear.echarts.components.marker.MarkLine2DDataItem;
import org.icepear.echarts.components.marker.MarkLine2DDataItemDim;
import org.icepear.echarts.components.marker.MarkPoint;
import org.icepear.echarts.components.marker.MarkPointDataItem;
import org.icepear.echarts.components.series.SeriesLineLabel;
import org.icepear.echarts.components.title.Title;
import org.icepear.echarts.components.toolbox.Toolbox;
import org.icepear.echarts.components.toolbox.ToolboxDataViewFeature;
import org.icepear.echarts.components.toolbox.ToolboxDataZoomFeature;
import org.icepear.echarts.components.toolbox.ToolboxMagicTypeFeature;
import org.icepear.echarts.components.toolbox.ToolboxRestoreFeature;
import org.icepear.echarts.components.toolbox.ToolboxSaveAsImageFeature;
import org.icepear.echarts.components.tooltip.Tooltip;
import org.icepear.echarts.origin.component.marker.MarkLineDataItemOption;
import org.icepear.echarts.origin.component.toolbox.ToolboxFeatureOption;
import org.icepear.echarts.origin.util.SeriesOption;
import org.icepear.echarts.render.Engine;
import org.junit.Before;
import org.junit.Test;

public class SnapshotWithToolBoxTest {
    private Option option;
    private String height = "600px";
    private String width = "600px";

    @Before
    public void buildOption() {
        Title title = new Title().setText("Temperature Change in the Coming Week");

        Tooltip tooltip = new Tooltip().setTrigger("axis");

        Legend legend = new Legend();

        Toolbox toolbox = new Toolbox()
                .setShow(true)
                .setFeature(new HashMap<String, ToolboxFeatureOption>() {
                    {
                        put("dataZoom", new ToolboxDataZoomFeature().setYAxisIndex("none"));
                        put("dataView", new ToolboxDataViewFeature().setReadOnly(false));
                        put("magicType", new ToolboxMagicTypeFeature()
                                .setType(new String[] { "line", "bar" }));
                        put("restore", new ToolboxRestoreFeature());
                        put("saveAsImage", new ToolboxSaveAsImageFeature());
                    }
                });

        CategoryAxis xAxis = new CategoryAxis()
                .setType("category")
                .setBoundaryGap(false)
                .setData(new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" });

        ValueAxis yAxis = new ValueAxis()
                .setType("value")
                .setAxisLabel(new ValueAxisLabel().setFormatter("{value} °C"));

        LineSeries series1 = new LineSeries()
                .setName("Highest")
                .setType("line")
                .setData(new Number[] { 10, 11, 13, 11, 12, 12, 9 })
                .setMarkPoint(new MarkPoint()
                        .setData(new MarkPointDataItem[] {
                                new MarkPointDataItem()
                                        .setType("max")
                                        .setName("Max"),
                                new MarkPointDataItem()
                                        .setType("min")
                                        .setName("Min") }))
                .setMarkLine(new MarkLine()
                        .setData(new MarkLine1DDataItem[] {
                                new MarkLine1DDataItem()
                                        .setType("average")
                                        .setName("Avg") }));

        LineSeries series2 = new LineSeries()
                .setName("Lowest")
                .setType("line")
                .setData(new Number[] { 1, -2, 2, 5, 3, 2, 0 })
                .setMarkPoint(new MarkPoint()
                        .setData(new MarkPointDataItem[] {
                                new MarkPointDataItem()
                                        .setName("周最低")
                                        .setValue(-2)
                                        .setXAxis(1)
                                        .setYAxis(-1.5) }))
                .setMarkLine(new MarkLine()
                        .setData(new MarkLineDataItemOption[] {
                                new MarkLine1DDataItem()
                                        .setType("average")
                                        .setName("Avg"),
                                new MarkLine2DDataItem()
                                        .setStartPoint(new MarkLine2DDataItemDim()
                                                .setSymbol("none")
                                                .setX("90%")
                                                .setYAxis("max"))
                                        .setEndPoint(new MarkLine2DDataItemDim()
                                                .setSymbol("circle")
                                                .setLabel(new SeriesLineLabel()
                                                        .setPosition("start")
                                                        .setFormatter("Max"))
                                                .setType("max")
                                                .setName("最高点")) }));

        Option option = new Option()
                .setTitle(title)
                .setTooltip(tooltip)
                .setLegend(legend)
                .setToolbox(toolbox)
                .setXAxis(xAxis)
                .setYAxis(yAxis)
                .setSeries(new SeriesOption[] { series1, series2 });
        this.option = option;
    }

    @Test
    public void testTakeSnapshot() throws Exception{
        Engine engine = new Engine();
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
        engine.render("./test.html", option);
        System.out.println(Snapshot.takeSnapshot(builder));
    }

    @Test
    public void testSaveSnapshotBase64() throws Exception{
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png");
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.txt");
    }

    @Test
    public void testSaveSnapshotPNG() throws Exception{
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "png", height, width, 1);
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.png");
    }

    @Test
    public void testSaveSnapshotJPG() throws Exception{
        SnapshotSettingsBuilder builder = new SnapshotSettingsBuilder(option, "jpg", height, width, 1);
        Snapshot.saveSnapshot(Snapshot.takeSnapshot(builder), "./test.jpg");
    }
}
