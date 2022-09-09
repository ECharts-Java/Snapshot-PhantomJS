package org.icepear.echarts;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class SnapshotSettingsBuilder {
    private Option option = null;
    private Chart<?, ?> chart = null;
    private String fileType = "png";
    private int delay = 2;
    // https://developer.mozilla.org/en-US/docs/Web/API/Window/devicePixelRatio#value
    private double pixelRatio = 2D;

    public SnapshotSettingsBuilder(Option option, String fileType) {
        this.option = option;
        this.fileType = fileType;
    }

    public SnapshotSettingsBuilder(Option option, String fileType, int delay) {
        this.option = option;
        this.fileType = fileType;
        this.chart = null;
        this.delay = delay;
    }

    public SnapshotSettingsBuilder(Option option, String fileType, double pixelRatio) {
        this.option = option;
        this.fileType = fileType;
        this.pixelRatio = pixelRatio;
    }

    public SnapshotSettingsBuilder(Option option, String fileType, int delay, double pixelRatio) {
        this.option = option;
        this.fileType = fileType;
        this.delay = delay;
        this.pixelRatio = pixelRatio;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String fileType) {
        this.chart = chart;
        this.fileType = fileType;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String fileType, int delay) {
        this.chart = chart;
        this.fileType = fileType;
        this.delay = delay;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String fileType, double pixelRatio) {
        this.chart = chart;
        this.fileType = fileType;
        this.pixelRatio = pixelRatio;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String fileType, int delay, double pixelRatio) {
        this.chart = chart;
        this.fileType = fileType;
        this.delay = delay;
        this.pixelRatio = pixelRatio;
    }
}
