package com.mobcb.statement.mpchart.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mobcb.statement.R;
import com.mobcb.statement.mpchart.AuthHelper;
import com.mobcb.statement.mpchart.JsonUtils;
import com.mobcb.statement.mpchart.bean.ChartBean;
import com.mobcb.statement.mpchart.bean.ChartDataListBean;
import com.mobcb.statement.mpchart.bean.ChartDetailBean;
import com.mobcb.statement.mpchart.bean.ResponseBean;
import com.mobcb.statement.mpchart.service.ChartService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalBarChartFragment extends Fragment {
    private static final String TAG = NormalBarChartFragment.class.getSimpleName();
    private String ids = "";
    private View mRoot;
    private BarChart mChart;
    private List<ChartDataListBean> chartDataList;


    public NormalBarChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_normal_bar_chart, container, false);
        getArg();
        initView(mRoot);
        getChartDetail();
        return mRoot;
    }

    private void getArg() {
        if (getArguments() != null) {
            ids = getArguments().getString("ids", "");
        }
    }

    private void initView(View mRoot) {
        mChart = (BarChart) mRoot.findViewById(R.id.chart);
    }

    private void getChartDetail() {
        AuthHelper.auth(ChartService.class)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<ChartService>() {
                    @Override
                    public void call(ChartService chartService) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("ids", ids);
                        Observable<ResponseBean> observable = chartService.getChart(map);
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<ResponseBean>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(ResponseBean responseBean) {
                                        if (responseBean == null) {
                                            Toast.makeText(getActivity(), "服务异常", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        String json = JsonUtils.toJson(responseBean.getBody());
                                        Log.d(TAG, "onNext: json = " + json);
                                        ChartBean chartBean = JsonUtils.toJavaObject(json, ChartBean.class);
                                        dealWithChartBean(chartBean);
                                    }
                                });
                    }
                });
    }


    private void dealWithChartBean(ChartBean chartBean) {
        mChart.getDescription().setEnabled(false);
//        mChart.setDrawBorders(true);
        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);


        float groupSpace = 0.08f;
        float barSpace = 0.03f;
        float barWidth = 0.4f;

        ArrayList<BarDataSet> barDataSets = new ArrayList<>();
        List<List<BarEntry>> yValues = new ArrayList<>();
        List<String> yDesc = new ArrayList<>();
        List<String> xDesc = new ArrayList<>();


        if (chartBean != null) {
            List<ChartDetailBean> chartList = chartBean.getChartList();
            if (chartList != null && !chartList.isEmpty()) {

                //每个表有几个柱子
                ChartDetailBean chartDetailBeanTemp = chartList.get(0);
                if (chartDetailBeanTemp != null) {
                    List<ChartDataListBean> chartDataList = chartDetailBeanTemp.getChartDataList();
                    for (int i = 0; i < chartDataList.size(); i++) {
                        List<BarEntry> entries = new ArrayList<>();
                        yValues.add(entries);
                        try {
                            yDesc.add(chartDataList.get(i).getXDesc());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


                //每个柱子的值
                for (ChartDetailBean chartDetailBean : chartList) {
                    if (chartDetailBean != null) {
                        xDesc.add(chartDetailBean.getName());
                        List<ChartDataListBean> chartDataList = chartDetailBean.getChartDataList();
                        for (int i = 0; i < chartDataList.size(); i++) {
                            ChartDataListBean chartDataListBean = chartDataList.get(i);
                            List<BarEntry> entries = yValues.get(i);
                            entries.add(new BarEntry(chartDataListBean.getXValue(), chartDataListBean.getYValue()));
                        }
                    }
                }
            }
        }


        //将柱子的值,每个柱子的描述,赋值给图表
        for (int i = 0; i < yValues.size(); i++) {
            BarDataSet barDataSet = new BarDataSet(yValues.get(i), yDesc.get(i));
            barDataSet.setColor(getColors(i).get(i));
            barDataSets.add(barDataSet);
        }

        //如果已经有值,则重新设置数据
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            for (int i = 0; i < mChart.getData().getDataSetCount(); i++) {
                if (i < barDataSets.size()) {
                    ((BarDataSet) mChart.getData().getDataSetByIndex(i))
                            .setValues(barDataSets.get(i).getValues());
                }
            }
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();

        } else {
            //之前没有值,
            // create 4 DataSets
            BarDataSet[] barDataSets1 = new BarDataSet[barDataSets.size()];
            for (int i = 0; i < barDataSets.size(); i++) {
                barDataSets1[i] = barDataSets.get(i);
            }
            BarData data = new BarData(barDataSets1);
//            data.setValueFormatter(new LargeValueFormatter());
            mChart.setData(data);
        }


        // specify the width each bar should have
        mChart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        mChart.groupBars(0, groupSpace, barSpace);
        mChart.invalidate();


        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xAxis = mChart.getXAxis();

        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value >= 0 && xDesc != null && xDesc.size() > value) {
                    return xDesc.get((int) value);
                }
                return String.valueOf(value);
            }
        });
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(xDesc != null ? xDesc.size() : 5);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mChart.getAxisRight().setEnabled(false);
    }

    protected ArrayList<Integer> getColors(int index) {

        ArrayList<Integer> colors = new ArrayList<Integer>();

        switch (index) {
            case 0:
                for (int c : ColorTemplate.PASTEL_COLORS)
                    colors.add(c);

                break;
            case 1:
                for (int c : ColorTemplate.JOYFUL_COLORS)
                    colors.add(c);

                break;
            case 2:
                for (int c : ColorTemplate.VORDIPLOM_COLORS)
                    colors.add(c);

                break;
            case 3:
                for (int c : ColorTemplate.COLORFUL_COLORS)
                    colors.add(c);

                break;
            case 4:
                for (int c : ColorTemplate.LIBERTY_COLORS)
                    colors.add(c);
                break;
            case 5:
                colors.add(ColorTemplate.getHoloBlue());
                break;
            default:
                for (int c : ColorTemplate.MATERIAL_COLORS) {
                    colors.add(c);
                }
                break;
        }

        return colors;
    }
}
