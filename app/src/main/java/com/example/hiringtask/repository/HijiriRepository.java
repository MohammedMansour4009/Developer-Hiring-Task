package com.example.hiringtask.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hiringtask.Room.EventsDatabase;
import com.example.hiringtask.dao.HijriRemoteDao;
import com.example.hiringtask.model.RemoteConvert;
import com.example.hiringtask.network.HttpStatus;


public class HijiriRepository {
    private HijriRemoteDao hijriRemoteDao;

    private MutableLiveData<RemoteConvert> eventsMutableLiveData;

    private String TAG="HijiriRepository";

    public HijiriRepository(Application application) {
        EventsDatabase database = EventsDatabase.getInstance(application);

        eventsMutableLiveData=new MutableLiveData<>();


        hijriRemoteDao = HijriRemoteDao.getInstance();
    }

    public MutableLiveData<RemoteConvert> getAllEvents(String date ) {

        HijriRemoteDao.getInstance().getData(date).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    Log.d(TAG, "getRemoteHome: Success");
                    eventsMutableLiveData.setValue(result.getResult());
                    break;
                default:
                    Log.d(TAG, " not  Success  " + result.getCode() + " " + result.getThrowable());
            }
        });
        return eventsMutableLiveData;
    }

}
