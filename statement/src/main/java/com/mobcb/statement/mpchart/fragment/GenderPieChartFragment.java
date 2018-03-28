package com.mobcb.statement.mpchart.fragment;


import android.support.v4.app.Fragment;

import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.mobcb.statement.mpchart.bean.MemberPieChartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenderPieChartFragment extends BasePieChartFragment {
    public static final String KEY_BUNDLE_GENDER_LIST = "key.bundle.gender.list";
    List<MemberPieChartBean.GenderBean> list;

    public GenderPieChartFragment() {
        // Required empty public constructor
    }

    public void getArg() {
        if (getArguments() != null) {
            list = getArguments().getParcelableArrayList(KEY_BUNDLE_GENDER_LIST);
            dealWithData(list);
        }
    }

    private void dealWithData(List<MemberPieChartBean.GenderBean> gender) {
        if (gender != null && gender.size() > 0) {
            ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
            for (MemberPieChartBean.GenderBean bean : gender) {
                entries.add(new PieEntry(bean.getNum(), bean.getMemberGender()));
            }
            PieDataSet dataSet = new PieDataSet(entries, "性别分布图");
            setPieDataSet(dataSet);
        }
    }

}
