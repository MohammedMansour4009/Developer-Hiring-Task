package com.example.hiringtask.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Events.class, version = 1)
@TypeConverters(Converters.class)

public abstract class EventsDatabase extends RoomDatabase {

    private static EventsDatabase instance;

    public abstract EventsDao eventsDao();


    public synchronized static EventsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), EventsDatabase.class, "events_database")
                    .fallbackToDestructiveMigration()//save last data ,when and new data
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventsDao noteDao;
        private PopulateDbAsyncTask(EventsDatabase db) {
                noteDao = db.eventsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insertEvents(new Events());
            noteDao.insertEvents(new Events());
            noteDao.insertEvents(new Events());
            return null;
        }
    }
}
