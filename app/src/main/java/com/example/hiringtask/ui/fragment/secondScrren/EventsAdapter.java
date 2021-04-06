package com.example.hiringtask.ui.fragment.secondScrren;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hiringtask.R;
import com.example.hiringtask.Room.Events;
import com.example.hiringtask.Room.EventsDatabase;
import com.example.hiringtask.databinding.RowEventsBinding;
import com.example.hiringtask.ui.fragment.firstScrren.EventsDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.carViewHolder> {
    private List<Events> eventsList;
    //    private EventsDatabase eventsDatabase;
    Context context;
    FragmentManager fragmentManager;
    RecyclerView recyclerView;

    private SecondScreenViewModel screenViewModel;

    public EventsAdapter(FragmentManager fragmentManager, SecondScreenViewModel secondScreenViewModel) {//Because get data from out
        this.screenViewModel = secondScreenViewModel;

//        eventsDatabase =EventsDatabase.getInstance(context);
        this.fragmentManager = fragmentManager;
        this.eventsList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
        this.recyclerView = recyclerView;

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
                screenViewModel.delete(events);
                removeAt(position);

            }
        });


        holder.binding.ivIcEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventsDialog exampleDialog = new EventsDialog(events, screenViewModel, recyclerView);
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