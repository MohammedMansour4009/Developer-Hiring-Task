package com.example.hiringtask.ui.fragment.secondScrren;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hiringtask.R;
import com.example.hiringtask.Room.Events;
import com.example.hiringtask.databinding.RowEventsBinding;
import com.example.hiringtask.ui.fragment.firstScrren.EventsDialog;

import java.util.ArrayList;
import java.util.List;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private List<Events> eventsList;
    private FragmentManager fragmentManager;
    private RecyclerView recyclerView;

    private SecondScreenViewModel screenViewModel;

    public EventsAdapter(FragmentManager fragmentManager, SecondScreenViewModel secondScreenViewModel) {//Because get data from out
        this.screenViewModel = secondScreenViewModel;

        this.fragmentManager = fragmentManager;
        this.eventsList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;

    }

    public void setEventsList(List<Events> EventsList) {
        this.eventsList = EventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//inflate of layout and Components
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_events, parent, false);
        return new EventViewHolder(RowEventsBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {// put new data ever time
        Events events = eventsList.get(position);
//        holder.binding.setModel(events);
        holder.binding.etDescriptionDialog.setText(events.getDescription());
        holder.binding.etNameEventDialog.setText(events.getName());
        holder.binding.tvTime.setText(events.getServerDatetime());
        holder.binding.tvHijri.setText(events.getHijriDate());
        holder.binding.tvGregorian.setText(events.getGregorianDate());

        holder.binding.ivIcDelete.setOnClickListener(v -> {
            screenViewModel.delete(events);
            removeAt(position);

        });


        holder.binding.ivIcEdit.setOnClickListener(v -> {
            EventsDialog eventsDialog = new EventsDialog(events, screenViewModel, recyclerView);
            eventsDialog.show(fragmentManager, "exampleDialog");
        });

    }


    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {//declare elements and but resources
        RowEventsBinding binding;

        public EventViewHolder(@NonNull RowEventsBinding binding) {
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