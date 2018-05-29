package com.mobcb.statement.mpchart.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mobcb.statement.R;
import com.mobcb.statement.mpchart.bean.MChart;
import com.mobcb.statement.mpchart.bean.MChartData;

import java.util.ArrayList;
import java.util.List;

public class CombinedChartActivity extends AppCompatActivity {

    private CombinedChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined_chart);
        initView();
    }

    private void initView() {
        mChart = (CombinedChart) findViewById(R.id.chart);

        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);

        // draw bars behind lines
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter());

        CombinedData data = new CombinedData();

        data.setData(generateLineData());
        data.setData(generateBarData());



        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        mChart.setData(data);
        mChart.invalidate();
    }

    private BarData generateBarData() {
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
            set1.setColor(Color.BLUE);
            set2 = new BarDataSet(yVals2, chart2.getName());
            //set2.setColor(Color.rgb(164, 228, 251));
            set2.setColor(Color.YELLOW);

            BarData data = new BarData(set2,set1);
            data.setValueFormatter(new LargeValueFormatter());
            //data.setValueTypeface(mTfLight);

            return data;
        }
        return null;
    }


    @SuppressLint("NewApi")
    private LineData generateLineData() {
        List<MChart> chartList = new ArrayList<>();

        MChart chart1 = new MChart();
        chart1.setName("会员");
        chart1.setType("member_count");
        List<MChartData> list1 = new ArrayList<>();
        MChartData jin1 = new MChartData();
        jin1.setxDesc("2018-03-27");
        jin1.setyValue(100);
        jin1.setxValue(0);
        MChartData yin1 = new MChartData();
        yin1.setxDesc("2018-03-28");
        yin1.setyValue(200);
        yin1.setxValue(1);
        MChartData tong1 = new MChartData();
        tong1.setxDesc("2018-03-29");
        tong1.setyValue(500);
        list1.add(jin1);
        list1.add(yin1);
        list1.add(tong1);
        tong1.setxValue(2);
        chart1.setChartDataList(list1);

        MChart chart2 = new MChart();
        chart2.setName("积分");
        chart2.setType("total_credit");
        List<MChartData> list2 = new ArrayList<>();
        MChartData jin2 = new MChartData();
        jin2.setxDesc("2018-03-27");
        jin2.setyValue(1000);
        jin2.setxValue(0);
        MChartData yin2 = new MChartData();
        yin2.setxDesc("2018-03-28");
        yin2.setyValue(800);
        yin2.setxValue(1);
        MChartData tong2 = new MChartData();
        tong2.setxDesc("2018-03-29");
        tong2.setyValue(600);
        list2.add(jin2);
        list2.add(yin2);
        list2.add(tong2);
        tong2.setxValue(2);
        chart2.setChartDataList(list2);

        chartList.add(chart1);
        chartList.add(chart2);

        float maxY = 0;
        float maxX = 0;


        if (chartList != null && chartList.size() > 0) {
            List<LineDataSet> sets = new ArrayList<>();
            for (int i = 0; i < chartList.size(); i++) {
                MChart mMChart = chartList.get(i);
                ArrayList<Entry> yVals = new ArrayList<Entry>();
                if (mMChart != null) {
                    List<MChartData> chartDataList = mMChart.getChartDataList();
                    if (chartDataList != null && chartDataList.size() > 0) {
                        maxX = chartDataList.size() > maxX ? chartDataList.size() : maxX;
                        for (int j = 0; j < chartDataList.size(); j++) {
                            MChartData mChartData = chartDataList.get(j);
                            if (mChartData != null) {
                                maxY = mChartData.getyValue() > maxY ? mChartData.getyValue() : maxY;
                                yVals.add(new Entry(mChartData.getxValue(), mChartData.getyValue(), mChartData.getxDesc()));
                            }
                        }
                        LineDataSet set = new LineDataSet(yVals, mMChart.getName());
                        set.setAxisDependency(YAxis.AxisDependency.LEFT);
                        set.setColors(getColors(i));
                        set.setCircleColor(Color.WHITE);
                        set.setLineWidth(2f);
                        set.setCircleRadius(3f);
                        set.setFillAlpha(65);
                        set.setFillColor(ColorTemplate.getHoloBlue());
                        set.setHighLightColor(Color.RED);
                        set.setDrawCircleHole(false);
                        sets.add(set);
                    }
                }
            }
            LineDataSet[] lineDataSets = new LineDataSet[sets.size()];
            for (int i = 0; i < sets.size(); i++) {
                lineDataSets[i] = sets.get(i);
            }
            LineData data = new LineData(lineDataSets);
            return data;
        }
        return null;
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