package com.example.hiringtask.ui.fragment.secondScrren;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    private EventsDatabase eventsDatabase;

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
    }

    private void init() {
        eventsDatabase = EventsDatabase.getInstance(getContext());
        eventsAdapter = new EventsAdapter();

        eventsDatabase.postsDao().getEvents()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())//because setList you  should work in main thread
                .subscribe(new SingleObserver<List<Events>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Events> posts) {
                        eventsAdapter.setEventsList(posts);
                       binding.rvEvents.setAdapter(eventsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }
}