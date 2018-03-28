package com.mobcb.statement.mpchart.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sanguine on 2018/3/26.
 */

public class MemberPieChartBean implements Serializable, Parcelable {
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

    public static class GenderBean implements Serializable, Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.memberGender);
            dest.writeInt(this.num);
            dest.writeInt(this.consumption);
        }

        public GenderBean() {
        }

        protected GenderBean(Parcel in) {
            this.memberGender = in.readString();
            this.num = in.readInt();
            this.consumption = in.readInt();
        }

        public static final Parcelable.Creator<GenderBean> CREATOR = new Parcelable.Creator<GenderBean>() {
            @Override
            public GenderBean createFromParcel(Parcel source) {
                return new GenderBean(source);
            }

            @Override
            public GenderBean[] newArray(int size) {
                return new GenderBean[size];
            }
        };
    }

    public static class LevelBean implements Serializable, Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.memberLevel);
            dest.writeString(this.memberLevelCode);
            dest.writeInt(this.num);
            dest.writeInt(this.credit);
            dest.writeInt(this.deposit);
            dest.writeInt(this.consumption);
        }

        public LevelBean() {
        }

        protected LevelBean(Parcel in) {
            this.memberLevel = in.readString();
            this.memberLevelCode = in.readString();
            this.num = in.readInt();
            this.credit = in.readInt();
            this.deposit = in.readInt();
            this.consumption = in.readInt();
        }

        public static final Parcelable.Creator<LevelBean> CREATOR = new Parcelable.Creator<LevelBean>() {
            @Override
            public LevelBean createFromParcel(Parcel source) {
                return new LevelBean(source);
            }

            @Override
            public LevelBean[] newArray(int size) {
                return new LevelBean[size];
            }
        };
    }

    public static class AgeBean implements Serializable, Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.memberAgeSection);
            dest.writeInt(this.num);
            dest.writeInt(this.consumption);
            dest.writeString(this.desc);
        }

        public AgeBean() {
        }

        protected AgeBean(Parcel in) {
            this.memberAgeSection = in.readInt();
            this.num = in.readInt();
            this.consumption = in.readInt();
            this.desc = in.readString();
        }

        public static final Parcelable.Creator<AgeBean> CREATOR = new Parcelable.Creator<AgeBean>() {
            @Override
            public AgeBean createFromParcel(Parcel source) {
                return new AgeBean(source);
            }

            @Override
            public AgeBean[] newArray(int size) {
                return new AgeBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.gender);
        dest.writeTypedList(this.level);
        dest.writeTypedList(this.age);
    }

    public MemberPieChartBean() {
    }

    protected MemberPieChartBean(Parcel in) {
        this.gender = in.createTypedArrayList(GenderBean.CREATOR);
        this.level = in.createTypedArrayList(LevelBean.CREATOR);
        this.age = in.createTypedArrayList(AgeBean.CREATOR);
    }

    public static final Parcelable.Creator<MemberPieChartBean> CREATOR = new Parcelable.Creator<MemberPieChartBean>() {
        @Override
        public MemberPieChartBean createFromParcel(Parcel source) {
            return new MemberPieChartBean(source);
        }

        @Override
        public MemberPieChartBean[] newArray(int size) {
            return new MemberPieChartBean[size];
        }
    };
}
