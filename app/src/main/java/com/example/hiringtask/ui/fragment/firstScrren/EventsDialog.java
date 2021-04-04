package com.example.hiringtask.ui.fragment.firstScrren;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiringtask.Room.Events;
import com.example.hiringtask.Room.EventsDatabase;
import com.example.hiringtask.Room.User;
import com.example.hiringtask.databinding.ContentForDialogBinding;
import com.example.hiringtask.model.Data;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.Calendar;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EventsDialog extends AppCompatDialogFragment {

    private final String TAG = "EventsDialog";
    private Data data;

    private ContentForDialogBinding binding;
    private EventsDatabase eventsDatabase;
    private Events mEvents;
    private RecyclerView recyclerView;

    public EventsDialog() {
    }

    public EventsDialog(Events events, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        mEvents = events;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        init();
        //when save new event
        if (data != null) {
            binding = ContentForDialogBinding.inflate(getLayoutInflater());
            builder.setView(binding.getRoot()).setTitle("Add event");

            binding.tvGregorian.setText(data.getGregorian().getDay() + "-" + data.getGregorian().getMonth().getNumber() + "-" + data.getGregorian().getYear() + "-");
            binding.tvHijri.setText(data.getHijri().getDay() + "-" + data.getHijri().getMonth().getNumber() + "-" + data.getHijri().getYear() + "-");
            binding.bSaveDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveDataInRoom();
                    dismiss();

                }
            });
        }
        //update event
        else {
            binding = ContentForDialogBinding.inflate(getLayoutInflater());
            builder.setView(binding.getRoot()).setTitle("update event");

            binding.tvGregorian.setText(mEvents.getGregorianDate());
            binding.tvHijri.setText(mEvents.getHijriDate());
            binding.bSaveDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    updateDataInRoom();
                    recyclerView.getAdapter().notifyDataSetChanged();
                    dismiss();
                }
            });

        }

        return builder.create();
    }

    private void init() {
        eventsDatabase = EventsDatabase.getInstance(getContext());
    }

    private void updateDataInRoom() {
        mEvents.setDescription(binding.etDescriptionDialog.getText().toString());
        mEvents.setName(binding.etNameEventDialog.getText().toString());
        eventsDatabase.postsDao().update(mEvents)
                .subscribeOn(Schedulers.computation())//s1
                .subscribe(new CompletableObserver() {//s2
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void saveDataInRoom() {
        eventsDatabase.postsDao().insertEvents(new Events(new User(1, "m7md "), binding.etNameEventDialog.getText().toString(), binding.etDescriptionDialog.getText().toString(), binding.tvGregorian.getText().toString(), binding.tvHijri.getText().toString(), Calendar.getInstance().getTime().toString()))
                //work in background u should work in anther thread
                .subscribeOn(Schedulers.computation())//s1
                .subscribe(new CompletableObserver() {//s2
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
