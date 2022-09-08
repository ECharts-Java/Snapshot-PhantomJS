package org.icepear.echarts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnapshotSettingsBuilder {
    private Option option = null;
    private Chart<?, ?> chart = null;
    private String path = "";
    private String fileType = "png";
    private int delay = 2;
    // https://developer.mozilla.org/en-US/docs/Web/API/Window/devicePixelRatio#value
    private double pixelRatio = 2D;

    public SnapshotSettingsBuilder(Option option, String path, String fileType) {
        this.option = option;
        this.path = path;
        this.fileType = fileType;
    }

    public SnapshotSettingsBuilder(Option option, String path, String fileType, int delay) {
        this.option = option;
        this.path = path;
        this.fileType = fileType;
        this.chart = null;
        this.delay = delay;
    }

    public SnapshotSettingsBuilder(Option option, String path, String fileType, double pixelRatio) {
        this.option = option;
        this.path = path;
        this.fileType = fileType;
        this.pixelRatio = pixelRatio;
    }

    public SnapshotSettingsBuilder(Option option, String path, String fileType, int delay, double pixelRatio) {
        this.option = option;
        this.path = path;
        this.fileType = fileType;
        this.delay = delay;
        this.pixelRatio = pixelRatio;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String path, String fileType) {
        this.chart = chart;
        this.path = path;
        this.fileType = fileType;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String path, String fileType, int delay) {
        this.chart = chart;
        this.path = path;
        this.fileType = fileType;
        this.delay = delay;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String path, String fileType, double pixelRatio) {
        this.chart = chart;
        this.path = path;
        this.fileType = fileType;
        this.pixelRatio = pixelRatio;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String path, String fileType, int delay, double pixelRatio) {
        this.chart = chart;
        this.path = path;
        this.fileType = fileType;
        this.delay = delay;
        this.pixelRatio = pixelRatio;
    }
}
