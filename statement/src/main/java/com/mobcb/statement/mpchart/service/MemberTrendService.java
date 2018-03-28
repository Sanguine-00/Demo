package com.mobcb.statement.mpchart.service;


import com.mobcb.statement.mpchart.bean.ResponseBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Sanguine on 2018/3/26.
 * 会员相关图表接口
 */

public interface MemberTrendService {

    /**
     * 会员增长趋势
     *
     * @param map
     * @return
     */
    @GET("api/v3/report/member/trend")
    Observable<ResponseBean> getTrend(@QueryMap Map<String, Object> map);

    /**
     * 会员年龄、性别、等级分布情况
     *
     * @return
     */
    @GET("http://192.168.1.94:10888/api/v3/report/memberinfo/info")
    Observable<ResponseBean> getPieInfo();


}
