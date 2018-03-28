package com.mobcb.statement.mpchart.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mobcb.statement.R;
import com.mobcb.statement.mpchart.AuthHelper;
import com.mobcb.statement.mpchart.FragmentManager;
import com.mobcb.statement.mpchart.JsonUtils;
import com.mobcb.statement.mpchart.bean.MemberPieChartBean;
import com.mobcb.statement.mpchart.bean.ResponseBean;
import com.mobcb.statement.mpchart.fragment.AgePieChartFragment;
import com.mobcb.statement.mpchart.fragment.GenderPieChartFragment;
import com.mobcb.statement.mpchart.fragment.LevelPieChartFragment;
import com.mobcb.statement.mpchart.service.MemberTrendService;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PieChartTestActivity extends FragmentActivity implements View.OnClickListener {

    protected PieChart mChart;
    /**
     * 年龄
     */
    private Button mStatementBtnPieChartAge;
    /**
     * 性别
     */
    private Button mStatementBtnPieChartGender;
    /**
     * 等级
     */
    private Button mStatementBtnPieChartLevel;
    private String TAG = PieChartTestActivity.class.getSimpleName();
    private MemberPieChartBean memberPieChartBean;
    private LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pie_chart_test);
        initView();
        setData();
    }

    private void initView() {
        mChart = (PieChart) findViewById(R.id.chart);
        initChartView();
        content = (LinearLayout) findViewById(R.id.content);
    }

    public void initChartView() {
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

//        mChart.setCenterTextTypeface(mTfLight);
//        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(false);
//        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart.setOnChartValueSelectedListener(this);
    }

    private void setData() {
//        MChart bean = new MChart();
//        bean.setName("会员");
//        bean.setType("会员");
//        List<MChartData> list = new ArrayList<>();
//
//
//        MChartData jin = new MChartData();
//        jin.setxDesc("会员等级");
//
//        List<Coordinate> jinList = new ArrayList<>();
//        Coordinate jinCoordinateNum = new Coordinate();
//        jinCoordinateNum.setDesc("初出茅庐");
//        jinCoordinateNum.setyValue(40);
//        jinList.add(jinCoordinateNum);
//        Coordinate jinCoordinateCredit = new Coordinate();
//        jinCoordinateCredit.setDesc("老司机");
//        jinCoordinateCredit.setyValue(60);
//        jinList.add(jinCoordinateCredit);
//
//        jin.setCoordinates(jinList);
//
//        MChartData yin = new MChartData();
//        yin.setxDesc("性别分布");
//
//        List<Coordinate> yinList = new ArrayList<>();
//
//        Coordinate yinCoordinateNum = new Coordinate();
//        yinCoordinateNum.setDesc("男");
//        yinCoordinateNum.setyValue(60);
//        yinList.add(yinCoordinateNum);
//
//        Coordinate yinCoordinateCredit = new Coordinate();
//        yinCoordinateCredit.setDesc("女");
//        yinCoordinateCredit.setyValue(300);
//        yinList.add(yinCoordinateCredit);
//
//        yin.setCoordinates(yinList);
//
//        list.add(jin);
//        list.add(yin);
//
//        bean.setChartDataList(list);
//
//
//        if (bean != null) {
//            List<MChartData> list1 = bean.getChartDataList();
//            if (list1 != null && list1.size() > 0) {
//                List<PieDataSet> dataSets = new ArrayList<>();
//
//
//                for (int i = 0; i < list1.size(); i++) {
//                    MChartData chartBean = list1.get(i);
//                    ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
//                    for (int j = 0; j < chartBean.getCoordinates().size(); j++) {
//                        Coordinate coordinate = chartBean.getCoordinates().get(j);
//                        entries.add(new PieEntry(coordinate.getyValue(), coordinate.getDesc()));
//                    }
//                    PieDataSet dataSet = new PieDataSet(entries, chartBean.getxDesc());
//                    dataSet.setDrawIcons(false);
//                    dataSet.setSliceSpace(3f);
//                    dataSet.setIconsOffset(new MPPointF(0, 40));
//                    dataSet.setSelectionShift(5f);
//                    // add a lot of colors
//                    ArrayList<Integer> colors = getColors(i);
//                    if (colors != null) {
//                        dataSet.setColors(colors);
//                    }
//                    //dataSet.setSelectionShift(0f);
//                    dataSets.add(dataSet);
//
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT);
//                    layoutParams.gravity = Gravity.CENTER;
//                    layoutParams.setMargins(10, 10, 10, 10);
//
//                    Button button = new Button(this);
//                    button.setText(chartBean.getxDesc());
//                    button.setLayoutParams(layoutParams);
//
//                    int finalI = i;
//                    button.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            PieData data = new PieData(dataSets.get(finalI));
//                            data.setValueFormatter(new PercentFormatter());
//                            data.setValueTextSize(11f);
//                            data.setValueTextColor(Color.WHITE);
//                            mChart.setData(data);
//
//                            // undo all highlights
//                            mChart.highlightValues(null);
//
//                            mChart.invalidate();
//                        }
//                    });
//                    content.addView(button);
//                }
//
//
//                PieData data = new PieData(dataSets.get(0));
//                data.setValueFormatter(new PercentFormatter());
//                data.setValueTextSize(11f);
//                data.setValueTextColor(Color.WHITE);
//                mChart.setData(data);
//
//                // undo all highlights
//                mChart.highlightValues(null);
//
//                mChart.invalidate();
//            }
//        }


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.statement_btn_pie_chart_age) {
            showAgeFragment();

        } else if (i == R.id.statement_btn_pie_chart_gender) {
            showGenderFragment();

        } else if (i == R.id.statement_btn_pie_chart_level) {
            showLevelFragment();

        } else {
        }
    }

    private void getData() {
        AuthHelper.auth(MemberTrendService.class)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<MemberTrendService>() {
                    @Override
                    public void call(MemberTrendService memberTrendService) {
                        Observable<ResponseBean> observable = memberTrendService.getPieInfo();
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
                                            return;
                                        }
                                        String json = JsonUtils.toJson(responseBean.getBody());
                                        Log.d(TAG, "onNext: json = " + json);
                                        memberPieChartBean = JsonUtils.toJavaObject(json, MemberPieChartBean.class);
                                        showAgeFragment();
                                    }
                                });
                    }
                });
    }

    /**
     * 显示年龄饼状图
     */
    private void showAgeFragment() {
        if (memberPieChartBean != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(AgePieChartFragment.KEY_BUNDLE_AGE_LIST, (ArrayList<? extends Parcelable>) memberPieChartBean.getAge());
            FragmentManager.getInstance(this).showFragment(AgePieChartFragment.class.getName(), bundle);
        }
    }

    /**
     * 显示性别饼状图
     */
    private void showGenderFragment() {
        if (memberPieChartBean != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(GenderPieChartFragment.KEY_BUNDLE_GENDER_LIST, (ArrayList<? extends Parcelable>) memberPieChartBean.getGender());
            FragmentManager.getInstance(this).showFragment(GenderPieChartFragment.class.getName(), bundle);
        }
    }

    /**
     * 显示等级饼状图
     */
    private void showLevelFragment() {
        if (memberPieChartBean != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(LevelPieChartFragment.KEY_BUNDLE_LEVEL_LIST, (ArrayList<? extends Parcelable>) memberPieChartBean.getLevel());
            FragmentManager.getInstance(this).showFragment(LevelPieChartFragment.class.getName(), bundle);
        }
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
