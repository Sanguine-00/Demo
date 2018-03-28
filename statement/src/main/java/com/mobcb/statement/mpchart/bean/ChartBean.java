package com.mobcb.statement.mpchart.bean;

import java.util.List;

/**
 * Created by Sanguine on 2018/3/27.
 * 坐标实体类
 */

public class ChartBean {

    private String xDesc;//横坐标描述
    private float yValue;//纵坐标的值
    private List<Coordinate> coordinates;//柱状图中多个柱子的集合

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
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
}
