package com.example.devinetproject.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Level;
import com.example.devinetproject.repository.level.LevelRepository;
import com.example.devinetproject.repository.level.LevelRepositoryImp;

import java.util.List;

public class LevelVm extends AndroidViewModel {
    LiveData<List<Level>> observer;
    LiveData<Level> observerById;
    LevelRepository levelRepository;

    public LevelVm(@NonNull Application application) {
        super(application);
        levelRepository = new LevelRepositoryImp(application);
        observer = levelRepository.get();
    }

    public void insert(Level ... levels){
        levelRepository.insert(levels);
    }

    public LiveData<List<Level>> get(){
        return observer;
    }

    public LiveData<Level> getById(int id){
        observerById = levelRepository.getById(id);
        return observerById;
    }

    public void update(Level ... levels){
        levelRepository.update(levels);
    }

    public void delete(Level ... levels){
        levelRepository.delete(levels);
    }

    public void deleteAll(){
        levelRepository.deleteAll();
    }
}
