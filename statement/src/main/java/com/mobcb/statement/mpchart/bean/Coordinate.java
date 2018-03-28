package com.mobcb.statement.mpchart.bean;

/**
 * Created by Sanguine on 2018/3/28.
 * 柱状图多个柱子的时候用到这个,折线图的每个点坐标
 */

public class Coordinate {
    private String desc;//纵坐标描述,例如会员表中的  数量,积分
    private float yValue;//纵坐标的值

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getyValue() {
        return yValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }
}
