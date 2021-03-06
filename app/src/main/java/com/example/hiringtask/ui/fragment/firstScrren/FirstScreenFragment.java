package com.example.hiringtask.ui.fragment.firstScrren;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.hiringtask.databinding.ContentForDialogBinding;
import com.example.hiringtask.databinding.FragmentFirstScreenBinding;
import com.example.hiringtask.model.RemoteConvert;

import java.util.Calendar;

public class FirstScreenFragment extends Fragment {


    private static final String TAG = "FirstScreenFragment";
    private FragmentFirstScreenBinding binding;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private RemoteConvert remoteConvert;

    private String date;

    private FirstScreenViewModel firstScreenViewModel;
    private ContentForDialogBinding dialogBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstScreenBinding.inflate(getLayoutInflater());

        dialogBinding = ContentForDialogBinding.inflate(LayoutInflater.from(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initDatePickerDialog();
        initButtonConvert();
        initSaveDate();
        initButtonGoToEvents();
    }

    private void initButtonGoToEvents() {
        binding.bGoToEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = FirstScreenFragmentDirections.actionFirstScreenFragmentToSecondFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });
    }

    private void initButtonConvert() {
        binding.bConvert.setOnClickListener(v -> getRemoteHijri());
    }

    private void initSaveDate() {
        binding.bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (remoteConvert.getData() == null) {
                    Toast.makeText(getContext(), "Choose a date", Toast.LENGTH_SHORT).show();
                } else {
                    EventsDialog exampleDialog = new EventsDialog(remoteConvert.getData());
                    exampleDialog.show(getActivity().getSupportFragmentManager(), "exampleDialog");
                }
            }
        });

    }

    private void initDatePickerDialog() {
        Log.d(TAG, "onClick: " + " button dialog2");

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
        mOnDateSetListener = (view, year, month, dayOfMonth) -> {
            date = dayOfMonth + "-" + month + "-" + year;
            binding.tvGregorian.setText(date);
            firstScreenViewModel.getRemoteHome(date);
        };
    }


    private void init() {
        //initialize view model for  MVVM
        firstScreenViewModel = ViewModelProviders.of(this).get(FirstScreenViewModel.class);
    }

    private void getRemoteHijri() {
        firstScreenViewModel.mutableLiveData.observe(getViewLifecycleOwner(), remoteConvert -> {
            this.remoteConvert = remoteConvert;
            binding.setModel(this.remoteConvert);

        });
    }

}