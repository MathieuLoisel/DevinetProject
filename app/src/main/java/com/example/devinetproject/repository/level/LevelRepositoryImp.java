package com.example.devinetproject.repository.level;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Level;
import com.example.devinetproject.dal.AppDatabase;
import com.example.devinetproject.dal.LevelDao;

import java.util.List;

public class LevelRepositoryImp implements LevelRepository {

    LevelDao levelDao;

    public LevelRepositoryImp(Context context) {
        AppDatabase bdd = AppDatabase.getInstance(context);
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
