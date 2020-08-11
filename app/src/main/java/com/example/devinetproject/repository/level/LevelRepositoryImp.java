package com.example.devinetproject.repository.level;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Level;
import com.example.devinetproject.dal.AppDatabase;
import com.example.devinetproject.dal.LevelDao;

import java.util.List;

public class LevelRepositoryImp implements LevelRepository {

    AppDatabase bdd;
    LevelDao levelDao;

    public LevelRepositoryImp(Application application) {
        bdd = AppDatabase.getInstance(application);
        levelDao = bdd.getLevelDao();
    }

    @Override
    public void insert(final Level... levels) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.insert(levels);
            }
        });
    }

    @Override
    public LiveData<List<Level>> get() {
        return levelDao.get();
    }

    @Override
    public LiveData<Level> getById(int id) {
        return levelDao.getById(id);
    }

    @Override
    public void update(Level... levels) {
        levelDao.upate(levels);
    }

    @Override
    public void delete(Level... levels) {
        levelDao.delete(levels);
    }

    @Override
    public void deleteAll() {
        levelDao.deleteAll();
    }
}
