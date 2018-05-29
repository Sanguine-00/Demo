package com.mobcb.statement.mpchart.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;

import com.aphidmobile.flip.FlipViewController;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mobcb.statement.mpchart.bean.MChart;
import com.mobcb.statement.mpchart.bean.MChartData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LineChartTestActivity extends FragmentActivity implements OnChartValueSelectedListener {

    protected FlipViewController flipView;
    ArrayList<Entry> yVals = new ArrayList<Entry>();
    private String TAG = LineChartTestActivity.class.getSimpleName();
    private List<String> xDesc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        flipView = new FlipViewController(this);

        flipView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LineChart mChart = new LineChart(LineChartTestActivity.this);
                // add data
                getData(mChart);
                return mChart;
            }
        });

        setContentView(flipView);


    }

    ;

    private void getData(LineChart mChart) {

        mChart.setOnChartValueSelectedListener(this);

        // no description text
        mChart.getDescription().setEnabled(false);
        mChart.getDescription().setText("123123123");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.LTGRAY);


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
                                xDesc.add(mChartData.getxDesc());
                                maxY = mChartData.getyValue() > maxY ? mChartData.getyValue() : maxY;
                                yVals.add(new Entry(mChartData.getxValue(), mChartData.getyValue(), mChartData.getxDesc()));
                            }
                        }
                        LineDataSet set = new LineDataSet(yVals, mMChart.getName());
                        set.setAxisDependency(AxisDependency.LEFT);
                        set.setColor(getColors(i).get(i));
                        set.setCircleColor(Color.WHITE);
                        set.setLineWidth(2f);
                        set.setCircleRadius(3f);
                        set.setFillAlpha(65);
                        set.setFillColor(ColorTemplate.getHoloBlue());
                        set.setHighLightColor(getColors(i).get(1));
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
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);
            // set data
            mChart.setData(data);
            setStyle(mChart, maxX, maxY);
        }
    }

    private void setStyle(LineChart mChart, float maxX, float maxY) {
        mChart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d(TAG, "getFormattedValue: value=" + value);
                try {
                    return ((LineDataSet) mChart.getLineData().getDataSetByIndex(0)).getValues().get((int) value).getData().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return String.valueOf(value);
            }
        });
        xAxis.setAxisMaximum(maxX);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(maxY);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTextColor(Color.LTGRAY);
        rightAxis.setAxisMaximum(900);
        rightAxis.setAxisMinimum(-200);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
    }


//    private void setData(int count, float range) {
//
//        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
//
//        for (int i = 0; i < count; i++) {
//            float mult = range / 2f;
//            float val = (float) (Math.random() * mult) + 50;
//            yVals1.add(new Entry(i, val));
//        }
//
//        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
//
//        for (int i = 0; i < count - 1; i++) {
//            float mult = range;
//            float val = (float) (Math.random() * mult) + 450;
//            yVals2.add(new Entry(i, val));
////            if(i == 10) {
////                yVals2.add(new Entry(i, val + 50));
////            }
//        }
//
//        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
//
//        for (int i = 0; i < count; i++) {
//            float mult = range;
//            float val = (float) (Math.random() * mult) + 500;
//            yVals3.add(new Entry(i, val));
//        }
//
//        LineDataSet set1, set2, set3;
//
//        if (mChart.getData() != null &&
//                mChart.getData().getDataSetCount() > 0) {
//            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
//            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
//            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
//            set1.setValues(yVals1);
//            set2.setValues(yVals2);
//            set3.setValues(yVals3);
//            mChart.getData().notifyDataChanged();
//            mChart.notifyDataSetChanged();
//        } else {
//            // create a dataset and give it a type
//            set1 = new LineDataSet(yVals1, "DataSet 1");
//
//            set1.setAxisDependency(AxisDependency.LEFT);
//            set1.setColor(ColorTemplate.getHoloBlue());
//            set1.setCircleColor(Color.WHITE);
//            set1.setLineWidth(2f);
//            set1.setCircleRadius(3f);
//            set1.setFillAlpha(65);
//            set1.setFillColor(ColorTemplate.getHoloBlue());
//            set1.setHighLightColor(Color.rgb(244, 117, 117));
//            set1.setDrawCircleHole(false);
//            //set1.setFillFormatter(new MyFillFormatter(0f));
//            //set1.setDrawHorizontalHighlightIndicator(false);
//            //set1.setVisible(false);
//            //set1.setCircleHoleColor(Color.WHITE);
//
//            // create a dataset and give it a type
//            set2 = new LineDataSet(yVals2, "DataSet 2");
//            set2.setAxisDependency(AxisDependency.RIGHT);
//            set2.setColor(Color.RED);
//            set2.setCircleColor(Color.WHITE);
//            set2.setLineWidth(2f);
//            set2.setCircleRadius(3f);
//            set2.setFillAlpha(65);
//            set2.setFillColor(Color.RED);
//            set2.setDrawCircleHole(false);
//            set2.setHighLightColor(Color.rgb(244, 117, 117));
//            //set2.setFillFormatter(new MyFillFormatter(900f));
//
//            set3 = new LineDataSet(yVals3, "DataSet 3");
//            set3.setAxisDependency(AxisDependency.RIGHT);
//            set3.setColor(Color.YELLOW);
//            set3.setCircleColor(Color.WHITE);
//            set3.setLineWidth(2f);
//            set3.setCircleRadius(3f);
//            set3.setFillAlpha(65);
//            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
//            set3.setDrawCircleHole(false);
//            set3.setHighLightColor(Color.rgb(244, 117, 117));
//
//            // create a data object with the datasets
//            LineData data = new LineData(set1, set2, set3);
//            data.setValueTextColor(Color.WHITE);
//            data.setValueTextSize(9f);
//
//            // set data
//            mChart.setData(data);
//        }
//    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());

//        mChart.centerViewToAnimated(e.getX(), e.getY(), mChart.getData().getDataSetByIndex(h.getDataSetIndex())
//                .getAxisDependency(), 500);
        //mChart.zoomAndCenterAnimated(2.5f, 2.5f, e.getX(), e.getY(), mChart.getData().getDataSetByIndex(dataSetIndex)
        // .getAxisDependency(), 1000);
        //mChart.zoomAndCenterAnimated(1.8f, 1.8f, e.getX(), e.getY(), mChart.getData().getDataSetByIndex(dataSetIndex)
        // .getAxisDependency(), 1000);
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
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
