package com.example.hiringtask.ui.fragment;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hiringtask.dao.HijriRemoteDao;
import com.example.hiringtask.model.RemoteConvert;
import com.example.hiringtask.network.HttpStatus;

public class FirstScreenViewModel extends ViewModel {
    private final String TAG = "ExperienceViewModel";
    public MutableLiveData<RemoteConvert> mutableLiveData = new MediatorLiveData<>();


    public void getRemoteHome(String date) {
        HijriRemoteDao.getInstance().getData(date).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    Log.d(TAG, "getRemoteHome: Success");
                    mutableLiveData.setValue(result.getResult());
                    break;
                default:
                    Log.d(TAG, " not  Success  " + result.getCode() + " " + result.getThrowable());
            }
        });
    }


}
