package com.mobcb.statement.mpchart.bean;

/**
 * Created by Sanguine on 2018/4/3.
 */

public class ChartDataListBean {
    /**
     * xDesc : 初出茅庐
     * yValue : 20
     * xValue : 0
     */

    private String xDesc;//X轴名称
    private float yValue;//Y轴数值
    private float xValue;//X轴顺序或排名

    public String getXDesc() {
        return xDesc;
    }

    public void setXDesc(String xDesc) {
        this.xDesc = xDesc;
    }

    public float getYValue() {
        return yValue;
    }

    public void setYValue(float yValue) {
        this.yValue = yValue;
    }

    public float getXValue() {
        return xValue;
    }

    public void setXValue(float xValue) {
        this.xValue = xValue;
    }
}