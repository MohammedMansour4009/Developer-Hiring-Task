package com.example.hiringtask.network;


import com.example.hiringtask.constant.AppConstant;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpAuthHelper {

    private static HttpAuthHelper instance;
    private Retrofit retrofit;

    private HttpAuthHelper() {


        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URI)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(new CallAdapterFactory())
                .client(getClient())
                .build();
    }

    public static HttpAuthHelper getInstance() {
        synchronized (HttpAuthHelper.class) {
            if (instance == null) {
                instance = new HttpAuthHelper();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Content-Type", "application/json");
                    builder.addHeader("Accept", "application/json");
//                    if (PrefsUtils.getInstance().isLogin()) {
//                        builder.addHeader("Authorization", "Bearer " + PrefsUtils.getInstance().getAccessToken());
//                    }
//                    builder.addHeader("Accept-Language", LocalHelper.isLanguageEn() ? EN : AR);
                    return chain.proceed(builder.build());
                }).addNetworkInterceptor(new StethoInterceptor()).build();


    }


}
