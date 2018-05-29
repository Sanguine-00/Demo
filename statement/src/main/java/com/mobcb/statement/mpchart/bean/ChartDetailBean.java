package com.mobcb.statement.mpchart.bean;

import java.util.List;

/**
 * Created by Sanguine on 2018/4/3.
 */

public class ChartDetailBean {
    /**
     * chartDataList : [{"xDesc":"初出茅庐","yValue":20,"xValue":0},{"xDesc":"小试牛刀","yValue":10,"xValue":1}]
     * name : 会员等级数量分布
     * type : 1
     */

    private String name;
    private int type;
    private List<ChartDataListBean> chartDataList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ChartDataListBean> getChartDataList() {
        return chartDataList;
    }

    public void setChartDataList(List<ChartDataListBean> chartDataList) {
        this.chartDataList = chartDataList;
    }
}
