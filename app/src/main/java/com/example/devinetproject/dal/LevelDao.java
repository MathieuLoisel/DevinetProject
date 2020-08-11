package com.example.devinetproject.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.devinetproject.bo.Level;

import java.util.List;

@Dao
public interface LevelDao {
    @Insert
    void insert(Level ... levels);

    @Query("SELECT * FROM Level")
    LiveData<List<Level>> get();

    @Query("SELECT * FROM Level WHERE id = :id")
    LiveData<Level> getById(int id);

    @Update
    void upate(Level ... levels);

    @Delete
    void delete(Level ... levels);

    @Query("DELETE FROM Level")
    void deleteAll();
}
