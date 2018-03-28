package com.mobcb.statement.mpchart.bean;

import java.util.List;


public class MChartSet {
    private String title;   //图表的标题，当displayType为overlay时使用该字段

    private String displayType; //图表展示方式,取值overlay：图标层叠显示 tab：图表以tab页形式显示

    private List<MChart> chartList; //图表集合，列表中每个元素代表一张图表

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public List<MChart> getChartList() {
        return chartList;
    }

    public void setChartList(List<MChart> chartList) {
        this.chartList = chartList;
    }
}
