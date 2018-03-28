package com.mobcb.statement.mpchart.bean;

/**
 * Created by MyStudio on 2017/4/19.
 */

public class ApiBody {

    public ApiBody(String reqBody) {
        this.reqBody = reqBody;
    }

    private String reqBody;

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }
}
