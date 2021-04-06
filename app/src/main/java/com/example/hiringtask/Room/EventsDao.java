package com.example.hiringtask.Room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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


    @Delete
    Completable delete(Events events);

    @Update
    Completable update(Events events);

    @Query("SELECT * FROM post_table ")
    LiveData<List<Events>> getAllEvents();
}
