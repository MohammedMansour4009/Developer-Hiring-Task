package com.example.hiringtask.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Events.class, version = 1)
@TypeConverters(Converters.class)
public abstract class EventsDatabase extends RoomDatabase {
    private static EventsDatabase instance;
    public abstract EventsDao postsDao();

    public synchronized static EventsDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), EventsDatabase.class, "events_database")
                    .fallbackToDestructiveMigration()//save last data ,when and new data
                    .build();
        }
        return instance;
    }

}
