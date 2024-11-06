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
    private String height = "100%";
    private String width = "100%";
    private int delay = 2;
    // https://developer.mozilla.org/en-US/docs/Web/API/Window/devicePixelRatio#value
    private double pixelRatio = 1D;

    public SnapshotSettingsBuilder(Option option, String fileType) {
        this.option = option;
        this.fileType = fileType;
    }
    
    public SnapshotSettingsBuilder(Option option, String fileType, String height, String width, double pixelRatio) {
        this.option = option;
        this.fileType = fileType;
        this.height = height;
        this.width = width;
        this.pixelRatio = pixelRatio;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String fileType) {
        this.chart = chart;
        this.fileType = fileType;
    }

    public SnapshotSettingsBuilder(Chart<?,?> chart, String fileType, String height, String width, double pixelRatio) {
        this.chart = chart;
        this.fileType = fileType;
        this.height = height;
        this.width = width;
        this.pixelRatio = pixelRatio;
    }
}
