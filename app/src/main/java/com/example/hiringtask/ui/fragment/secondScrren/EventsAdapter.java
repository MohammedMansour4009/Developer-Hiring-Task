package com.example.hiringtask.ui.fragment.secondScrren;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hiringtask.R;
import com.example.hiringtask.Room.Events;
import com.example.hiringtask.databinding.RowEventsBinding;

import java.util.ArrayList;
import java.util.List;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.carViewHolder> {
    private List<Events> eventsList;

    public EventsAdapter( ) {//Because get data from out
        this.eventsList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
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


}