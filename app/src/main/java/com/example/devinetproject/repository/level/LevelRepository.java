package com.example.devinetproject.repository.level;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Level;

import java.util.List;

public interface LevelRepository {
    void insert(Level ... levels);
    LiveData<List<Level>> get();
    LiveData<Level> getById(int id);
    void update(Level ... levels);
    void delete(Level ... levels);
    void deleteAll();
}
