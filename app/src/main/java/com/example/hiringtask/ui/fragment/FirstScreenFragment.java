package com.example.hiringtask.ui.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hiringtask.databinding.FragmentFirstScreenBinding;
import com.example.hiringtask.model.RemoteConvert;

import java.util.Calendar;

public class FirstScreenFragment extends Fragment {


    private static final String TAG = "FirstScreenFragment";
    FragmentFirstScreenBinding binding;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private RemoteConvert mRemoteConvert;

    private String date;

    FirstScreenViewModel firstScreenViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstScreenBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initConvert();
    }

    private void initConvert() {
        binding.tvGregorian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mOnDateSetListener
                        , year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: " + year + "-" + month + "-" + dayOfMonth);
                date = dayOfMonth + "-" + month + "-" + year;
                binding.tvGregorian.setText(date);

                firstScreenViewModel.getRemoteHome(date);
                getRemoteInfoApp();


            }
        };
    }

//    private void getRemoteHome() {
//        HijriRemoteDao.getInstance().getData(date).enqueue(result -> {
//            switch (result.getStatus()) {
//                case HttpStatus.SUCCESS:
//                    Log.d(TAG, "getRemoteHome: Success");
//                    remoteConvert = result.getResult();
//                    String s = remoteConvert.getData().getHijri().getDay() + "-" + remoteConvert.getData().getHijri().getMonth() + "-" + remoteConvert.getData().getHijri().getYear();
//
//                    break;
//                default:
//                    Log.d(TAG, " not  Success  " + result.getCode() + " " + result.getThrowable());
//            }
//        });
//
//    }
    private void init() {
        firstScreenViewModel = ViewModelProviders.of(this).get(FirstScreenViewModel.class);
    }

    private void getRemoteInfoApp() {
        firstScreenViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<RemoteConvert>() {
            @Override
            public void onChanged(RemoteConvert remoteConvert) {
                mRemoteConvert=remoteConvert;

                binding.tvHijri.setText(mRemoteConvert.getData().getHijri().getDay()
                        + "-" + mRemoteConvert.getData().getHijri().getMonth().getNumber() +
                        "-" + mRemoteConvert.getData().getHijri().getYear());
//                binding.rvExperience.setAdapter(experienceAdapter);
//                binding.rvExperience.getAdapter().notifyDataSetChanged();
            }
        });
    }

}