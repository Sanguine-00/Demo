package com.mobcb.statement.mpchart.bean;

/**
 * Created by Sanguine on 2018/4/3.
 */

public class ChartListBean {
    /**
     * id : 2018040301
     * report_type : 1
     * report_name : 会员等级与积分
     */
    private String id;//报表ID
    private int report_type;//报表类型 1柱状图，2折线图，3饼图
    private String report_name;//报表名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReport_type() {
        return report_type;
    }

    public void setReport_type(int report_type) {
        this.report_type = report_type;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }
}
