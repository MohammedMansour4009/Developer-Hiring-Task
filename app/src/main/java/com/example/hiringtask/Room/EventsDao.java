package com.example.hiringtask.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface EventsDao {
    @Insert
    Completable insertEvents(Events events);
    @Query("select * from post_table")
    Single<List<Events>> getEvents();

}
