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
public class AgePieChartFragment extends BasePieChartFragment {
    public static final String KEY_BUNDLE_AGE_LIST = "key.bundle.age.list";
    List<MemberPieChartBean.AgeBean> list;

    public AgePieChartFragment() {
        // Required empty public constructor
    }

    public void getArg() {
        if (getArguments() != null) {
            list = getArguments().getParcelableArrayList(KEY_BUNDLE_AGE_LIST);
            dealWithData(list);
        }
    }

    private void dealWithData(List<MemberPieChartBean.AgeBean> age) {
        if (age != null && age.size() > 0) {
            ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
            for (MemberPieChartBean.AgeBean bean : age) {
                entries.add(new PieEntry(bean.getNum(), bean.getDesc()));
            }
            PieDataSet dataSet = new PieDataSet(entries, "年龄分布图");
            setPieDataSet(dataSet);
        }
    }


}
