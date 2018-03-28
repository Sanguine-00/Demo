package com.mobcb.statement.mpchart.bean;

import java.util.List;

/**
 * 图表实体类
 */
public class MChart {

    private String title;   //图表标题，当MChartSet中displayType为tab时，在每个子界面使用此标题
    private String name;    //数据名称，折线图或柱状图中使用
    private String type;    //哪一类的数据，暂时未使用，可作为保留字段
    private String chartType; //以何种形式展示,line：折线图，bar：柱状图，pie：饼图
    private List<MChartData> chartDataList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public List<MChartData> getChartDataList() {
        return chartDataList;
    }

    public void setChartDataList(List<MChartData> chartDataList) {
        this.chartDataList = chartDataList;
    }
}
