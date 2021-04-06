package com.example.hiringtask.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hiringtask.Room.Events;
import com.example.hiringtask.Room.EventsDao;
import com.example.hiringtask.Room.EventsDatabase;

import java.util.List;

public class EventsRepository {
    private EventsDao eventsDao;

    private LiveData<List<Events>> eventsMutableLiveData;

    public EventsRepository(Application application) {
        EventsDatabase database = EventsDatabase.getInstance(application);

        eventsDao = database.eventsDao();

        eventsMutableLiveData = eventsDao.getAllEvents();

    }

    public void insert(Events events) {
        new InsertEventAsyncTask(eventsDao).execute(events);
    }

    public void update(Events events) {
        new UpdateEventAsyncTask(eventsDao).execute(events);
    }

    public void delete(Events events) {
        new DeleteEventAsyncTask(eventsDao).execute(events);
    }


    public LiveData<List<Events>> getAllEvents() {

        return eventsMutableLiveData;
    }

    private static class InsertEventAsyncTask extends AsyncTask<Events,Void,Void>{
        private  EventsDao eventsDao;

        public InsertEventAsyncTask(EventsDao eventsDao) {
            this.eventsDao = eventsDao;
        }

        @Override
        protected Void doInBackground(Events... events) {
            eventsDao.insertEvents(events[0]);
            return null;
        }
    }
    private static class UpdateEventAsyncTask extends AsyncTask<Events,Void,Void>{
        private  EventsDao eventsDao;

        public UpdateEventAsyncTask(EventsDao eventsDao) {
            this.eventsDao = eventsDao;
        }

        @Override
        protected Void doInBackground(Events... events) {
            eventsDao.update(events[0]);
            return null;
        }
    }
    private static class DeleteEventAsyncTask extends AsyncTask<Events,Void,Void>{
        private  EventsDao eventsDao;

        public DeleteEventAsyncTask(EventsDao eventsDao) {
            this.eventsDao = eventsDao;
        }

        @Override
        protected Void doInBackground(Events... events) {
            eventsDao.delete(events[0]);
            return null;
        }


    }

}

