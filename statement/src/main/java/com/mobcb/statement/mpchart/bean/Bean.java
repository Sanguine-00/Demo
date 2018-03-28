package com.mobcb.statement.mpchart.bean;

import java.util.List;

/**
 * Created by Sanguine on 2018/3/27.
 */

public class Bean {

    private String name;//表名
    private String type;//哪一类的数据
    private List<ChartBean> list;

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

    public List<ChartBean> getList() {
        return list;
    }

    public void setList(List<ChartBean> list) {
        this.list = list;
    }
}
