package com.example.hiringtask.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface EventsDao {
    @Insert
    Completable insertEvents(Events events);

    @Query("select * from post_table")
    Single<List<Events>> getEvents();
    @Delete
    Completable delete(Events events);

    @Update
    Completable update(Events events);


}
