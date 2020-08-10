package com.example.devinetproject.repository.word;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Word;
import com.example.devinetproject.dal.AppDatabase;
import com.example.devinetproject.dal.WordDao;

import java.util.List;

public class WordRepositoryImp implements WordRepository{

    private WordDao wordDao;
    private LiveData<List<Word>> words;

    public WordRepositoryImp(Application application){
        AppDatabase bdd = AppDatabase.getInstance(application);
        wordDao = bdd.getWordDao();
    }

    @Override
    public void insert(Word... words) {
        wordDao.insert(words);
    }

    @Override
    public LiveData<List<Word>> get() {
        return wordDao.get();
    }

    @Override
    public void update(Word... words) {
        wordDao.update(words);
    }

    @Override
    public void delete(Word... words) {
        wordDao.delete(words);
    }

    @Override
    public void deleteAll() {
        wordDao.deleteAll();
    }
}
