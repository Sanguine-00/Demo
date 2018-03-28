package com.example.rxjavademo.mpchart.service;

import com.example.rxjavademo.mpchart.bean.ResponseBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Sanguine on 2018/3/26.
 */

public interface MemberTrend {

    @GET("api/v3/report/member/trend")
    Observable<ResponseBean> getTrend(@QueryMap Map<String, Object> map);

    @GET("http://192.168.1.94:10888/api/v3/report/memberinfo/info")
    Observable<ResponseBean> getPieInfo();


}
