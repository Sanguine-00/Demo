package com.example.rxjavademo.mpchart.bean;

import java.util.List;

/**
 * Created by Sanguine on 2018/3/26.
 */

public class PieChartBean {
    private List<GenderBean> gender;
    private List<LevelBean> level;
    private List<AgeBean> age;

    public List<GenderBean> getGender() {
        return gender;
    }

    public void setGender(List<GenderBean> gender) {
        this.gender = gender;
    }

    public List<LevelBean> getLevel() {
        return level;
    }

    public void setLevel(List<LevelBean> level) {
        this.level = level;
    }

    public List<AgeBean> getAge() {
        return age;
    }

    public void setAge(List<AgeBean> age) {
        this.age = age;
    }

    public static class GenderBean {
        /**
         * memberGender : 女
         * num : 100
         * consumption : 100000
         */

        private String memberGender;
        private int num;
        private int consumption;

        public String getMemberGender() {
            return memberGender;
        }

        public void setMemberGender(String memberGender) {
            this.memberGender = memberGender;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getConsumption() {
            return consumption;
        }

        public void setConsumption(int consumption) {
            this.consumption = consumption;
        }
    }

    public static class LevelBean {
        /**
         * memberLevel : 小试牛刀
         * memberLevelCode : 1
         * num : 10
         * credit : 0
         * deposit : 0
         * consumption : 0
         */

        private String memberLevel;
        private String memberLevelCode;
        private int num;
        private int credit;
        private int deposit;
        private int consumption;

        public String getMemberLevel() {
            return memberLevel;
        }

        public void setMemberLevel(String memberLevel) {
            this.memberLevel = memberLevel;
        }

        public String getMemberLevelCode() {
            return memberLevelCode;
        }

        public void setMemberLevelCode(String memberLevelCode) {
            this.memberLevelCode = memberLevelCode;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getConsumption() {
            return consumption;
        }

        public void setConsumption(int consumption) {
            this.consumption = consumption;
        }
    }

    public static class AgeBean {
        /**
         * memberAgeSection : 1
         * num : 10
         * consumption : 0
         * desc : 小于十岁
         */

        private int memberAgeSection;
        private int num;
        private int consumption;
        private String desc;

        public int getMemberAgeSection() {
            return memberAgeSection;
        }

        public void setMemberAgeSection(int memberAgeSection) {
            this.memberAgeSection = memberAgeSection;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getConsumption() {
            return consumption;
        }

        public void setConsumption(int consumption) {
            this.consumption = consumption;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
