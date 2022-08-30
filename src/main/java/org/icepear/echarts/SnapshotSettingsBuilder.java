package org.icepear.echarts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnapshotSettingsBuilder {
    private Option option;
    private Chart<?, ?> chart;
    private String path;
    private String fileType;
    private int delay;
    private int pixelRatio;

    public SnapshotSettingsBuilder(Option option, String path, String fileType) {
        this.option = option;
        this.path = path;
        this.fileType = fileType;
        this.chart = null;
        this.delay = 2;
        this.pixelRatio = 2;
    }
}
