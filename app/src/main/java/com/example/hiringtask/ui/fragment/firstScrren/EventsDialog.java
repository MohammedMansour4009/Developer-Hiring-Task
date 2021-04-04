package com.example.hiringtask.ui.fragment.firstScrren;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.hiringtask.Room.Events;
import com.example.hiringtask.Room.EventsDatabase;
import com.example.hiringtask.Room.User;
import com.example.hiringtask.databinding.ContentForDialogBinding;
import com.example.hiringtask.model.Data;

import java.util.Calendar;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EventsDialog extends AppCompatDialogFragment {

    private final String TAG = "EventsDialog";
    private Data data;

    ContentForDialogBinding binding;
    private EventsDatabase eventsDatabase;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

          binding = ContentForDialogBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot()).setTitle("Add event");

        binding.tvGregorian.setText(data.getGregorian().getDay()+"-"+data.getGregorian().getMonth().getNumber()+"-"+data.getGregorian().getYear()+"-");
        binding.tvHijri.setText(data.getHijri().getDay()+"-"+data.getHijri().getMonth().getNumber()+"-"+data.getHijri().getYear()+"-");
        binding.bSaveDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataInRoom();
                dismiss();

            }
        });

        return builder.create();
    }

    private void saveDataInRoom() {
        eventsDatabase = EventsDatabase.getInstance(getContext());
        eventsDatabase.postsDao().insertEvents(new Events(new User(1,"m7md "),binding.etNameEventDialog.getText().toString(), binding.etDescriptionDialog.getText().toString(),binding.tvGregorian.getText().toString(),binding.tvHijri.getText().toString(), Calendar.getInstance().getTime().toString()))
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
