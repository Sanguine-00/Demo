package com.mobcb.statement.mpchart.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mobcb.statement.R;
import com.mobcb.statement.mpchart.bean.MChart;
import com.mobcb.statement.mpchart.bean.MChartData;

import java.util.ArrayList;
import java.util.List;

public class BarChartTestActivity extends Activity implements OnChartValueSelectedListener {

    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_line_chart_test);
        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);
        mChart.getDescription().setEnabled(false);

//        mChart.setDrawBorders(true);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//        mv.setChartView(mChart); // For bounds control
//        mChart.setMarker(mv); // Set the marker to the chart


        List<MChart> chartList = new ArrayList<>();

        MChart chart1 = new MChart();
        chart1.setName("会员");
        chart1.setType("member_count");
        List<MChartData> list1 = new ArrayList<>();
        MChartData jin1 = new MChartData();
        jin1.setxDesc("金卡");
        jin1.setyValue(100);
        MChartData yin1 = new MChartData();
        yin1.setxDesc("银卡");
        yin1.setyValue(200);
        MChartData tong1 = new MChartData();
        tong1.setxDesc("铜卡");
        tong1.setyValue(500);
        list1.add(jin1);
        list1.add(yin1);
        list1.add(tong1);
        chart1.setChartDataList(list1);

        MChart chart2 = new MChart();
        chart2.setName("积分");
        chart2.setType("total_credit");
        List<MChartData> list2 = new ArrayList<>();
        MChartData jin2 = new MChartData();
        jin2.setxDesc("金卡");
        jin2.setyValue(1000);
        MChartData yin2 = new MChartData();
        yin2.setxDesc("银卡");
        yin2.setyValue(800);
        MChartData tong2 = new MChartData();
        tong2.setxDesc("铜卡");
        tong2.setyValue(600);
        list2.add(jin2);
        list2.add(yin2);
        list2.add(tong2);
        chart2.setChartDataList(list2);

        chartList.add(chart1);
        chartList.add(chart2);


        float groupSpace = 0.08f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.4f; // x4 DataSet

        List<BarEntry> yVals1 = new ArrayList<BarEntry>();
        List<BarEntry> yVals2 = new ArrayList<BarEntry>();

        List<MChartData> listOne = chartList.get(0).getChartDataList();

        for (int i = 0; i < listOne.size(); i++) {
            yVals1.add(new BarEntry(i, chartList.get(0).getChartDataList().get(i).getyValue()));
            yVals2.add(new BarEntry(i, chartList.get(1).getChartDataList().get(i).getyValue()));
        }

        BarDataSet set1, set2;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();

        } else {
            // create 2 DataSets
            set1 = new BarDataSet(yVals1, chart1.getName());
            //set1.setColor(Color.rgb(104, 241, 175));
            set1.setColor(getColors(0).get(1));
            set2 = new BarDataSet(yVals2, chart2.getName());
            //set2.setColor(Color.rgb(164, 228, 251));
            set2.setColor(getColors(1).get(1));

            BarData data = new BarData(set1, set2);
            data.setValueFormatter(new LargeValueFormatter());
            //data.setValueTypeface(mTfLight);

            mChart.setData(data);
        }

        List<String> xDesc = new ArrayList<>();

        //先看每个x有几个柱子
        List<MChartData> cFirst = chartList.get(0).getChartDataList();
        if (cFirst != null && cFirst.size() > 0) {
            for (int i = 0; i < cFirst.size(); i++) {
                MChartData chartBean = cFirst.get(i);
                xDesc.add(chartBean.getxDesc());
            }
        }

//======================================================================================
//        ArrayList<BarDataSet> barDataSets = new ArrayList<>();
//
//        List<List<BarEntry>> yValues = new ArrayList<>();
//        List<String> yDesc = new ArrayList<>();

//
//        //再给柱子赋值,每个x有几个柱子,那么就有几个List<BarEntry>,所以一次循环搞不定
//        for (int j = 0; j < c1.size(); j++) {
//            MChartData chartBean = c1.get(j);
//            if (chartBean != null) {
//                xDesc.add(chartBean.getxDesc());
//                for (int i = 0; i < yValues.size(); i++) {
//                    List<BarEntry> yVals = yValues.get(i);
//                    yVals.add(new BarEntry(j, chartBean.getyValue()));
//                }
//            }
//        }
//
//        //将柱子的值,每个x的柱子的描述,赋值给图表
//        for (int i = 0; i < yValues.size(); i++) {
//            BarDataSet barDataSet = new BarDataSet(yValues.get(i), yDesc.get(i));
//            barDataSet.setColor(colors.get(i).get(i));
//            barDataSets.add(barDataSet);
//        }
//
//        //如果已经有值,则重新设置数据
//        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
//            for (int i = 0; i < mChart.getData().getDataSetCount(); i++) {
//                if (i < barDataSets.size()) {
//                    ((BarDataSet) mChart.getData().getDataSetByIndex(i))
//                            .setValues(barDataSets.get(i).getValues());
//                }
//            }
//            mChart.getData().notifyDataChanged();
//            mChart.notifyDataSetChanged();
//
//        } else {
//            //之前没有值,
//            // create 4 DataSets
//            BarDataSet[] barDataSets1 = new BarDataSet[barDataSets.size()];
//            for (int i = 0; i < barDataSets.size(); i++) {
//                barDataSets1[i] = barDataSets.get(i);
//            }
//            BarData data = new BarData(barDataSets1);
////            data.setValueFormatter(new LargeValueFormatter());
//            mChart.setData(data);
//        }

        // specify the width each bar should have
        mChart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        mChart.getXAxis().setAxisMinimum(0);

        mChart.getXAxis().setAxisMaximum(3);
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
                return String.valueOf((int) value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

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
