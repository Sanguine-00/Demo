package com.mobcb.statement.mpchart.bean;

/**
 * 图标数据实体类
 */
public class MChartData {

    private float xValue;   //横坐标的值,取值0,1,2,3,4,5,6......用于标记数据的展示顺序，如为空则按照List中数据默认顺序
    private String xDesc;   //横坐标描述
    private float yValue;   //纵坐标的值
    private String yDesc;   //纵坐标的描述

    public float getxValue() {
        return xValue;
    }

    public void setxValue(float xValue) {
        this.xValue = xValue;
    }

    public String getxDesc() {
        return xDesc;
    }

    public void setxDesc(String xDesc) {
        this.xDesc = xDesc;
    }

    public float getyValue() {
        return yValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }

    public String getyDesc() {
        return yDesc;
    }

    public void setyDesc(String yDesc) {
        this.yDesc = yDesc;
    }
}
