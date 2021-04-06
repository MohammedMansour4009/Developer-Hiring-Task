package com.example.hiringtask.ui.fragment.firstScrren;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hiringtask.dao.HijriRemoteDao;
import com.example.hiringtask.model.RemoteConvert;
import com.example.hiringtask.network.HttpStatus;
import com.example.hiringtask.repository.EventsRepository;
import com.example.hiringtask.repository.HijiriRepository;

public class FirstScreenViewModel extends ViewModel {
    private final String TAG = "ExperienceViewModel";
    public MutableLiveData<RemoteConvert> mutableLiveData = new MutableLiveData<>();
    private HijiriRepository hijiriRepository;

    public FirstScreenViewModel() {
        hijiriRepository = new HijiriRepository();

    }


    public void getRemoteHome(String date) {
        mutableLiveData = hijiriRepository.getAllEvents(date);
    }


}
