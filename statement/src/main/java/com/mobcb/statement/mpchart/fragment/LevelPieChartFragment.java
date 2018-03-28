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
public class LevelPieChartFragment extends BasePieChartFragment {
    public static final String KEY_BUNDLE_LEVEL_LIST = "key.bundle.level.list";
    private List<MemberPieChartBean.LevelBean> list;

    public LevelPieChartFragment() {
        // Required empty public constructor
    }

    public void getArg() {
        if (getArguments() != null) {
            list = getArguments().getParcelableArrayList(KEY_BUNDLE_LEVEL_LIST);
            dealWithData(list);
        }
    }

    private void dealWithData(List<MemberPieChartBean.LevelBean> level) {
        if (level != null && level.size() > 0) {
            ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
            for (MemberPieChartBean.LevelBean bean : level) {
                entries.add(new PieEntry(bean.getNum(), bean.getMemberLevel()));
            }
            PieDataSet dataSet = new PieDataSet(entries, "等级分布图");
            setPieDataSet(dataSet);
        }
    }

}
