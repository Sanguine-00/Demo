package com.mobcb.statement.mpchart.service;

import com.mobcb.statement.mpchart.bean.ResponseBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Sanguine on 2018/3/27.
 * 储值相关图表接口
 */

public interface StoreTrendService {
    /**
     * 储值总数、充值、消耗趋势
     *
     * @param map
     * @return
     */
    @GET("http://192.168.1.94:10888/api/v3/report/reportdeposit/reportinfo")
    Observable<ResponseBean> getStoreTrend(@QueryMap Map<String, Object> map);

    /**
     * 积储值按会员等级统计
     */
    @GET("http://192.168.1.94:10888/api/v3/report/reportdeposit/leveldeposit")
    Observable<ResponseBean> getMemberLevel();
}
