package com.example.hiringtask.network;



import com.example.hiringtask.constant.AppConstant;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class HttpHelper {

    private static HttpHelper instance;
    private Retrofit retrofit;
    private TrustManager[] trustManagers;
    private SSLContext trustAllSslContext;

    private HttpHelper() {
        trustManagers = getTrustManagers();
        initSslFactory(trustManagers);


        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URI)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .addCallAdapterFactory(new CallAdapterFactory())
                .client(getClient())
                .build();
    }


    public static HttpHelper getInstance() {
        synchronized (HttpHelper.class) {
            if (instance == null) {
                instance = new HttpHelper();
            }
        }
        return instance;
    }

    public static void setInstance(HttpHelper httpHelper) {
        instance = httpHelper;
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .sslSocketFactory(trustAllSslContext.getSocketFactory(), (X509TrustManager) trustManagers[0])
                .hostnameVerifier((hostname, session) -> true)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Accept-Language", "en");
                    builder.addHeader("latitude", "21.571029623177647");
                    builder.addHeader("longitude", "39.191507399082184");
                    builder.addHeader("AppVersion", "25");
                    builder.addHeader("os", "Android");
                    builder.addHeader("os", "Android");
                    builder.addHeader("nearest-branch-id", "1");
//                    builder.addHeader("Authorization",
//                            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwczovL2F1dGhvcml6YXRpb24uZXh0ZW5zaW9uL3NlY3VyaXR5Ijp7InBhcnRuZXItZGV2Ijp7Imhvc3QiOiJwYXJ0bmVyLnBhdGhvbmUudGVjaCIsInJvbGVzIjpbIlJPTEVfUEFSVE5FUl9BRE1JTiJdLCJwdWJudWIiOnsiYXV0aF9rZXkiOiJqMnJsQ3RZdGh3aEh6UkhIT3dUdndVUWpqa3padkJOQ0cxLVpFNmZzWkVnIiwidHRsIjozNjAwMCwiY2hhbm5lbCI6InBhcnRuZXItZGV2LioifX19LCJodHRwczovL3Byb2ZpbGUuaW5mbyI6eyJuYW1lIjoiVGVzdGVyIFRlc3QiLCJ1c2VybmFtZSI6InRlc3QiLCJlbWFpbCI6InRlc3RAdGVzdC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZX0sImlzcyI6Imh0dHBzOi8vZm9ya3kuZXUuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDU5OTZjMTc2NTE0NDRjNjE0NzlkY2JkNyIsImF1ZCI6InVybjpzYWFzOnBhcnRuZXI6YXJlYSIsImlhdCI6MTU5OTQxMDkzOCwiZXhwIjoxNTk5NjYyOTM4LCJhenAiOiJ2d1pHd0VEWEk3Sk5Iajd5SE91aTdob1ltZkhqTHZ0WCIsInNjb3BlIjoib2ZmbGluZV9hY2Nlc3MiLCJndHkiOlsicmVmcmVzaF90b2tlbiIsInBhc3N3b3JkIl19.zF4UwixaLexF5bPsaVZljhMRpzccjto0brYmnNBsL70");
//                    if (PrefsUtils.getInstance().isLogin()) {
//                        builder.addHeader("Authorization", "Bearer " + PrefsUtils.getInstance().getAccessToken());
//                    }
//                    builder.addHeader("Accept-Language", LocalHelper.isLanguageEn() ? EN : AR);
                    return chain.proceed(builder.build());
                }).addNetworkInterceptor(new StethoInterceptor()).build();


    }


    private TrustManager[] getTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }

    private SSLContext initSslFactory(TrustManager[] trustManagers) {
        try {
            trustAllSslContext = SSLContext.getInstance("SSL");
            trustAllSslContext.init(null, trustManagers, new java.security.SecureRandom());
            return trustAllSslContext;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }


}

