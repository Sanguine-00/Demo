package com.mobcb.statement.mpchart.service;

import com.mobcb.statement.mpchart.bean.ResponseBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Sanguine on 2018/3/27.
 * 积分相关图表接口
 */

public interface CreditTrendService {

    /**
     * 积分总数、发放、消耗趋势
     *
     * @param map
     * @return
     */
    @GET("http://192.168.1.94:10888/api/v3/report/reportcredit/reportinfo")
    Observable<ResponseBean> getCreditTrend(@QueryMap Map<String, Object> map);

    /**
     * 积分按会员等级统计
     */
    @GET("http://192.168.1.94:10888/api/v3/report/reportcredit/reportinfo")
    Observable<ResponseBean> getMemberLevel();
}
