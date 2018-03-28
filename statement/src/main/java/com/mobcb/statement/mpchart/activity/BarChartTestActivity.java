package com.mobcb.statement.mpchart.activity;

import android.app.Activity;
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
import com.mobcb.statement.mpchart.bean.Coordinate;
import com.mobcb.statement.mpchart.bean.Bean;
import com.mobcb.statement.mpchart.bean.ChartBean;

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
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//        mv.setChartView(mChart); // For bounds control
//        mChart.setMarker(mv); // Set the marker to the chart


        Bean bean = new Bean();
        bean.setName("会员");
        bean.setType("会员");
        List<ChartBean> list = new ArrayList<>();


        ChartBean jin = new ChartBean();
        jin.setxDesc("金卡");

        List<Coordinate> jinList = new ArrayList<>();
        Coordinate jinCoordinateNum = new Coordinate();
        jinCoordinateNum.setDesc("数量");
        jinCoordinateNum.setyValue(40);
        jinList.add(jinCoordinateNum);
        Coordinate jinCoordinateCredit = new Coordinate();
        jinCoordinateCredit.setDesc("积分");
        jinCoordinateCredit.setyValue(150);
        jinList.add(jinCoordinateCredit);

        jin.setCoordinates(jinList);

        ChartBean yin = new ChartBean();
        yin.setxDesc("银卡");

        List<Coordinate> yinList = new ArrayList<>();

        Coordinate yinCoordinateNum = new Coordinate();
        yinCoordinateNum.setDesc("数量");
        yinCoordinateNum.setyValue(60);
        yinList.add(yinCoordinateNum);

        Coordinate yinCoordinateCredit = new Coordinate();
        yinCoordinateCredit.setDesc("积分");
        yinCoordinateCredit.setyValue(300);
        yinList.add(yinCoordinateCredit);

        yin.setCoordinates(yinList);

        list.add(jin);
        list.add(yin);

        bean.setList(list);

        float groupSpace = 0.08f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.4f; // x4 DataSet

        ArrayList<BarDataSet> barDataSets = new ArrayList<>();

        List<List<BarEntry>> yValues = new ArrayList<>();
        List<String> yDesc = new ArrayList<>();
        List<String> xDesc = new ArrayList<>();
        List<List<Integer>> colors = new ArrayList<>();

        //先看每个x有几个柱子
        ChartBean chartBean = list.get(0);
        if (chartBean != null && chartBean.getCoordinates() != null && chartBean.getCoordinates().size() > 0) {
            List<Coordinate> coordinates = chartBean.getCoordinates();
            for (int i = 0; i < coordinates.size(); i++) {
                Coordinate yValue = coordinates.get(i);
                ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
                yValues.add(yVals);
                yDesc.add(yValue.getDesc());
                colors.add(getColors(i));
            }
        }

        //再给柱子赋值,每个x有几个柱子,那么就有几个List<BarEntry>,所以一次循环搞不定
        for (int j = 0; j < list.size(); j++) {
            ChartBean chartBean1 = list.get(j);
            if (chartBean1 != null) {
                xDesc.add(chartBean1.getxDesc());
                for (int i = 0; i < yValues.size(); i++) {
                    List<BarEntry> yVals = yValues.get(i);
                    yVals.add(new BarEntry(j, chartBean1.getCoordinates().get(i).getyValue()));
                }
            }
        }
//        List<BarEntry> yVals1 = new ArrayList<>();
//        List<BarEntry> yVals2 = new ArrayList<>();
////        for (int j = 0; j < list.size(); j++) {
//            ChartBean coordinate1 = list.get(0);
//            if (coordinate1 != null) {
//                xDesc.add(coordinate1.getxDesc());
//                yVals1.add(new BarEntry(1, coordinate1.getCoordinates().get(0).getyValue()));
//                yVals2.add(new BarEntry(1, coordinate1.getCoordinates().get(1).getyValue()));
//            }
//            ChartBean coordinate2 = list.get(1);
//            if (coordinate2 != null) {
//                xDesc.add(coordinate2.getxDesc());
//                yVals1.add(new BarEntry(2, coordinate2.getCoordinates().get(0).getyValue()));
//                yVals2.add(new BarEntry(2, coordinate2.getCoordinates().get(1).getyValue()));
//            }
////        }

        //将柱子的值,每个x的柱子的描述,赋值给图表
        for (int i = 0; i < yValues.size(); i++) {
            BarDataSet barDataSet = new BarDataSet(yValues.get(i), yDesc.get(i));
            barDataSet.setColor(colors.get(i).get(i));
            barDataSets.add(barDataSet);
        }

//        BarDataSet barDataSet1 = new BarDataSet(yVals1, yDesc.get(0));
//        barDataSet1.setColor(colors.get(0).get(0));
//        BarDataSet barDataSet2 = new BarDataSet(yVals2, yDesc.get(1));
//        barDataSet2.setColor(colors.get(0).get(1));


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
        mChart.getXAxis().setAxisMinimum(0);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
//        mChart.getXAxis().setAxisMaximum(0 + mChart.getBarData().getGroupWidth(groupSpace, barSpace) * 2);
        mChart.getXAxis().setAxisMaximum(5);
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
