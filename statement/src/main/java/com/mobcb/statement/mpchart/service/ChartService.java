package com.mobcb.statement.mpchart.service;

import com.mobcb.statement.mpchart.bean.ResponseBean;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Sanguine on 2018/4/3.
 */

public interface ChartService {

    /**
     * 获取可视化报表列表
     *
     * @return
     */
    @GET("http://192.168.1.94:10888/api/v3/report/getSettingReport/getList")
    Observable<ResponseBean> getChartList();

    /**
     * 获取可视化报表列表
     *
     * @return
     */
    @POST("http://192.168.1.94:10888/api/v3/report/getSettingReport/getData")
    Observable<ResponseBean> getChart(@Body Map<String, Object> map);
}
