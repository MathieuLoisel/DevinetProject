package com.example.devinetproject.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Word;
import com.example.devinetproject.repository.word.WordRepository;
import com.example.devinetproject.repository.word.WordRepositoryImp;

import java.util.List;

/**
 * Classe permettant la communication entre la vue (activity) et le repository
 */
public class WordVm extends AndroidViewModel {
    /**
     * Permet de n'avoir qu'un seul observateur pour toute l'application
     */
    private LiveData<List<Word>> observer;
    private WordRepository wordRepository;

    public WordVm(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepositoryImp(application);
        observer = wordRepository.get();
    }

    public LiveData<List<Word>> get(){
        return observer;
    }

    public void insert(Word ... words){
        wordRepository.insert(words);
    }

    public void update(Word ... words){
        wordRepository.update(words);
    }

    public void delete(Word ... words){
        wordRepository.delete(words);
    }

    public void deleteAll(){
        wordRepository.deleteAll();
    }

    public LiveData<List<Word>> getByLevel(int idLevel){
        return wordRepository.getByLevel(idLevel);
    }

    public LiveData<List<Word>> getByCategory(int idCategory){
        return wordRepository.getByCategory(idCategory);
    }
}
