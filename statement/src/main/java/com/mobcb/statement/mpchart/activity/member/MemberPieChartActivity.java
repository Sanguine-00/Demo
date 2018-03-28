package com.mobcb.statement.mpchart.activity.member;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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

public class MemberPieChartActivity extends FragmentActivity implements View.OnClickListener {

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
    private String TAG = MemberPieChartActivity.class.getSimpleName();
    private MemberPieChartBean memberPieChartBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.statement_activity_pie_chart);
        initView();
        getData();
    }

    private void initView() {
        mStatementBtnPieChartAge = (Button) findViewById(R.id.statement_btn_pie_chart_age);
        mStatementBtnPieChartAge.setOnClickListener(this);
        mStatementBtnPieChartGender = (Button) findViewById(R.id.statement_btn_pie_chart_gender);
        mStatementBtnPieChartGender.setOnClickListener(this);
        mStatementBtnPieChartLevel = (Button) findViewById(R.id.statement_btn_pie_chart_level);
        mStatementBtnPieChartLevel.setOnClickListener(this);
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
}
