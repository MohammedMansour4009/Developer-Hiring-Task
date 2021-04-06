package com.example.hiringtask.ui.fragment.secondScrren;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiringtask.Room.Events;
import com.example.hiringtask.Room.EventsDatabase;
import com.example.hiringtask.databinding.FragmentSecondBinding;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private EventsAdapter eventsAdapter;


    private SecondScreenViewModel screenViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getRemoteAllEvents();

    }

    private void init() {
        screenViewModel = ViewModelProviders.of(this).get(SecondScreenViewModel.class);

        eventsAdapter=new EventsAdapter(getFragmentManager(),screenViewModel);


    }

    private void getRemoteAllEvents() {
        screenViewModel.getAllEvents().observe(getViewLifecycleOwner(), events -> {
            eventsAdapter.setEventsList(events);
            binding.rvEvents.setAdapter(eventsAdapter);
        });
    }
}