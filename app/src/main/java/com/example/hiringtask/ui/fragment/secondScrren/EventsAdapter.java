package com.example.hiringtask.ui.fragment.secondScrren;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hiringtask.R;
import com.example.hiringtask.Room.Events;
import com.example.hiringtask.Room.EventsDatabase;
import com.example.hiringtask.databinding.RowEventsBinding;
import com.example.hiringtask.ui.fragment.firstScrren.EventsDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.carViewHolder> {
    private List<Events> eventsList;
    private EventsDatabase eventsDatabase;
    Context context;
    FragmentManager fragmentManager;
    RecyclerView recyclerView;
    public EventsAdapter(FragmentManager fragmentManager) {//Because get data from out
        eventsDatabase =EventsDatabase.getInstance(context);
        this.fragmentManager=fragmentManager;
        this.eventsList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context=recyclerView.getContext();
        this.recyclerView=recyclerView;

    }

    public void setEventsList(List<Events> EventsList) {
        this.eventsList = EventsList;
    }

    @NonNull
    @Override
    public EventsAdapter.carViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//inflate of layout and Components
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_events, parent, false);
        return new EventsAdapter.carViewHolder(RowEventsBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.carViewHolder holder, final int position) {// put new data ever time
        Events events = eventsList.get(position);
//        holder.binding.setModel(events);
        holder.binding.etDescriptionDialog.setText(events.getDescription());
        holder.binding.etNameEventDialog.setText(events.getName());
        holder.binding.tvTime.setText(events.getServerDatetime());
        holder.binding.tvHijri.setText(events.getHijriDate());
        holder.binding.tvGregorian.setText(events.getGregorianDate());

        holder.binding.ivIcDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsDatabase.postsDao().delete(events)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())//because setList you  should work in main thread
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                removeAt(position);
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                            }
                        });

            }
        });

        holder.binding.ivIcEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventsDialog exampleDialog = new EventsDialog(events,recyclerView);
                exampleDialog.show(fragmentManager, "exampleDialog");
            }
        });

    }


    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    class carViewHolder extends RecyclerView.ViewHolder {//declare elements and but resources
        RowEventsBinding binding;

        public carViewHolder(@NonNull RowEventsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
    public void removeAt(int position) {
        eventsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, eventsList.size());
    }

}