package com.mobcb.statement.mpchart.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.mobcb.statement.R;
import com.mobcb.statement.mpchart.AuthHelper;
import com.mobcb.statement.mpchart.ChartConstants;
import com.mobcb.statement.mpchart.JsonUtils;
import com.mobcb.statement.mpchart.bean.ChartListBean;
import com.mobcb.statement.mpchart.bean.ResponseBean;
import com.mobcb.statement.mpchart.fragment.NormalBarChartFragment;
import com.mobcb.statement.mpchart.service.ChartService;
import com.mobcb.statement.mpchart.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class NormalChartActivity extends FragmentActivity {

    private static final String TAG = NormalChartActivity.class.getSimpleName();
    private PagerSlidingTabStrip mIndicator;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_chart);
        initView();
        requestList();
    }

    private void initView() {
//        mIndicator = (PagerSlidingTabStrip) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void requestList() {
        AuthHelper.auth(ChartService.class)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<ChartService>() {
                    @Override
                    public void call(ChartService chartService) {
                        Observable<ResponseBean> observable = chartService.getChartList();
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<ResponseBean>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d(TAG, "onError: e" + e);
                                        Toast.makeText(NormalChartActivity.this, "服务异常", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNext(ResponseBean responseBean) {
                                        if (responseBean == null) {
                                            Toast.makeText(NormalChartActivity.this, "服务异常", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        String json = JsonUtils.toJson(responseBean.getBody());
                                        Log.d(TAG, "onNext: json = " + json);
                                        List<ChartListBean> chartListBeans = JsonUtils.toList(json, ChartListBean.class);
                                        dealWithChartList(chartListBeans);

                                    }
                                });
                    }
                });
    }

    private void dealWithChartList(List<ChartListBean> chartListBeans) {
        if (chartListBeans != null && chartListBeans.size() > 0) {
            List<Fragment> fragments = new ArrayList<>();
            List<String> title = new ArrayList<>();
            NormalBarChartFragment barChartFragment = null;
            String barChartIds = "";
            for (ChartListBean chartListBean : chartListBeans) {
                if (chartListBean != null) {
                    if (ChartConstants.CHART_TYPE_BARS == chartListBean.getReport_type()) {
                        //柱状图
                        if (barChartFragment == null) {
                            Bundle bundle = new Bundle();
                            barChartFragment = new NormalBarChartFragment();
                            barChartIds = barChartIds.concat(chartListBean.getId());
                            bundle.putString("ids", barChartIds);
                            barChartFragment.setArguments(bundle);
                            fragments.add(barChartFragment);
                            title.add(chartListBean.getReport_name());
                        } else {
                            barChartIds = barChartIds.concat(",").concat(chartListBean.getId());
                            barChartFragment.getArguments().putString("ids", barChartIds);
                            fragments.remove(barChartFragment);
                            fragments.add(barChartFragment);
                        }
                    }
                }
            }

            mViewPager.setAdapter(new MineViewPagerAdapter(getSupportFragmentManager(), fragments, title));
            mIndicator.setViewPager(mViewPager);
            mViewPager.setCurrentItem(0);

        }
    }

    static class MineViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;
        List<String> title;

        public MineViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> title) {
            super(fm);
            this.fragments = fragments;
            this.title = title;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title == null || position < 0 || position > title.size() ? "" : title.get(position);
        }
    }
}
