package com.example.hiringtask.dao;

import com.example.hiringtask.model.RemoteConvert;
import com.example.hiringtask.network.HttpHelper;
import com.example.hiringtask.network.MyCall;

import retrofit2.http.GET;
import retrofit2.http.Query;

public class HijriRemoteDao {
    private static  HijriRemoteDao instance;
    private DashboardHijri dashboardHijri;

    public HijriRemoteDao() {
        dashboardHijri = HttpHelper.getInstance().create(DashboardHijri.class);
    }


    public  static  synchronized  HijriRemoteDao getInstance(){
        if(instance ==null)
            instance=new HijriRemoteDao();
        return instance;
    }
    public MyCall<RemoteConvert> getData(String data ) {
        return dashboardHijri.getData(data);
    }


    private interface DashboardHijri {
        @GET("gToH")
        public MyCall<RemoteConvert> getData(@Query("date") String date);
    }
}
