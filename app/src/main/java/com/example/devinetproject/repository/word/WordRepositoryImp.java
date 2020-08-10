package com.example.devinetproject.repository.word;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Word;
import com.example.devinetproject.dal.AppDatabase;
import com.example.devinetproject.dal.WordDao;

import java.util.List;

/**
 * Classe implémentant l'interface WordRepository. Permet à la couche supérieure (VM) de faire des
 * appels à la BDD tout en repsectant la séparations des couhces de l'architexture component
 *
 * Classe permettant la communication entre la DAL et le ViewModel
 */
public class WordRepositoryImp implements WordRepository{

    private WordDao wordDao;
    private LiveData<List<Word>> words;

    public WordRepositoryImp(Application application){
        AppDatabase bdd = AppDatabase.getInstance(application);
        wordDao = bdd.getWordDao();
    }

    @Override
    public void insert(final Word... words) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.insert(words);
            }
        });
    }

    @Override
    public LiveData<List<Word>> get() {
        return wordDao.get();
    }

    @Override
    public void update(final Word... words) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.update(words);
            }
        });
    }

    @Override
    public void delete(final Word... words) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.delete(words);
            }
        });
    }

    @Override
    public void deleteAll() {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.deleteAll();
            }
        });
    }

}
