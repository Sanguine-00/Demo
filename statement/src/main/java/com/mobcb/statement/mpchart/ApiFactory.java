package com.mobcb.statement.mpchart;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sanguine on 2018/3/26.
 */

public class ApiFactory {
    private static final int DEFAULT_CONNECT_TIMEOUT = 15;   // 默认连接超时时间
    private static final int DEFAULT_WRITE_TIMEOUT = 15;   // 默认写入超时时间
    private static final int DEFAULT_READ_TIMEOUT = 15;   // 默认读取超时时间

    public static <T> T create(final Class<T> cls) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://192.168.0.184:10888/")//注意此处,设置服务器的地址
                // 使用自定义转换器，ThreeDES加解密在转换器中实现
                .addConverterFactory(GsonConverterFactory.create())// 用于Json数据的转换
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 用于返回Rxjava调用
                .client(genericClient());
        Retrofit retrofit = builder.build();
        return retrofit.create(cls);
    }

    public static OkHttpClient genericClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request oldRequest = chain.request();
                Request.Builder builder = oldRequest.newBuilder();
                HttpUrl httpUrl = oldRequest.url();
                HttpUrl.Builder urlBuilder = httpUrl.newBuilder();
                //.scheme(oldRequest.url().scheme())
                //.host(oldRequest.url().host())

                Map<String, String> queryMap = new HashMap<>();
                Set<String> queryParameterNames = httpUrl.queryParameterNames();
                for (String queryParameterName : queryParameterNames) {
                    String queryParameter = httpUrl.queryParameter(queryParameterName);
                    if (queryParameter != null) {
                        queryMap.put(queryParameterName, queryParameter);
                    }
                    urlBuilder.removeAllQueryParameters(queryParameterName);
                }
                long tm = System.currentTimeMillis() / 1000L;
                RequestBody requestBody = oldRequest.body();
                if (requestBody != null && requestBody.contentLength() > 0) {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    String bodyString = buffer.readUtf8();
                    if (!TextUtils.isEmpty(bodyString)) {
//                        requestBody = JsonUtils.createRequestBody(bodyString, tm);
                        buffer.clear();
                        requestBody.writeTo(buffer);
//                        queryMap.put(Sign.BODY, buffer.readUtf8());
                    }
                    buffer.close();
                }
                //queryString加签名等固定参数
                queryMap = Sign.sign(queryMap, tm);

                // 添加固定的参数
                for (Object o : queryMap.entrySet()) {
                    Map.Entry entry = (Map.Entry) o;
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    urlBuilder.addQueryParameter(key, value);
                }

                //添加请求头
                if (Config.encryptRsa) {
                    // rsa加密开启
                    builder.addHeader("EncryptRsa", "true");
                } else {
                    // rsa加密关闭
                    builder.addHeader("EncryptRsa", "false");
                }
                if (Config.encryptBody) {
                    // 业务加密开启
                    builder.addHeader("EncryptBody", "true");

                } else {
                    // 业务加密关闭
                    builder.addHeader("EncryptBody", "false");
                }

                builder.addHeader("checkSession", "false");

                Request newRequest = builder
                        .method(oldRequest.method(), requestBody)
                        .url(urlBuilder.build())
                        .build();
                Log.d("URL", newRequest.url().toString());
                return chain.proceed(newRequest);
            }
        });
        OkHttpClient httpClient = builder.build();
        return httpClient;
    }
}
