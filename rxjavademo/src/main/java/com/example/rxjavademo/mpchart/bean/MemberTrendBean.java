package com.example.rxjavademo.mpchart.bean;

import java.util.List;

/**
 * Created by Sanguine on 2018/3/26.
 */

public class MemberTrendBean {

    /**
     * code : PUB-00000
     * msg :
     * clientExecuteMethod :
     * body : [{"mdate":"2018-03-18","source":"","vipNum":1200,"addVipNum":1200,"dayActivityCount":10,"dayAddActivityCount":10,"dayAppAnimatoCount":10,"dayAppAddAnimatoCount":10}]
     */

    private String code;
    private String msg;
    private String clientExecuteMethod;
    private List<BodyBean> body;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getClientExecuteMethod() {
        return clientExecuteMethod;
    }

    public void setClientExecuteMethod(String clientExecuteMethod) {
        this.clientExecuteMethod = clientExecuteMethod;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * mdate : 2018-03-18
         * source :
         * vipNum : 1200
         * addVipNum : 1200
         * dayActivityCount : 10
         * dayAddActivityCount : 10
         * dayAppAnimatoCount : 10
         * dayAppAddAnimatoCount : 10
         */

        private String mdate;
        private String source;
        private int vipNum;
        private int addVipNum;
        private int dayActivityCount;
        private int dayAddActivityCount;
        private int dayAppAnimatoCount;
        private int dayAppAddAnimatoCount;

        public String getMdate() {
            return mdate;
        }

        public void setMdate(String mdate) {
            this.mdate = mdate;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getVipNum() {
            return vipNum;
        }

        public void setVipNum(int vipNum) {
            this.vipNum = vipNum;
        }

        public int getAddVipNum() {
            return addVipNum;
        }

        public void setAddVipNum(int addVipNum) {
            this.addVipNum = addVipNum;
        }

        public int getDayActivityCount() {
            return dayActivityCount;
        }

        public void setDayActivityCount(int dayActivityCount) {
            this.dayActivityCount = dayActivityCount;
        }

        public int getDayAddActivityCount() {
            return dayAddActivityCount;
        }

        public void setDayAddActivityCount(int dayAddActivityCount) {
            this.dayAddActivityCount = dayAddActivityCount;
        }

        public int getDayAppAnimatoCount() {
            return dayAppAnimatoCount;
        }

        public void setDayAppAnimatoCount(int dayAppAnimatoCount) {
            this.dayAppAnimatoCount = dayAppAnimatoCount;
        }

        public int getDayAppAddAnimatoCount() {
            return dayAppAddAnimatoCount;
        }

        public void setDayAppAddAnimatoCount(int dayAppAddAnimatoCount) {
            this.dayAppAddAnimatoCount = dayAppAddAnimatoCount;
        }
    }
}
