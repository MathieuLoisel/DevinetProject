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
    private WordRepository WordRepository;

    public WordVm(@NonNull Application application) {
        super(application);
        WordRepository = new WordRepositoryImp(application);
        observer = WordRepository.get();
    }

    public LiveData<List<Word>> get(){
        return observer;
    }

    public void insert(Word ... words){
        WordRepository.insert(words);
    }

    public void update(Word ... words){
        WordRepository.update(words);
    }

    public void delete(Word ... words){
        WordRepository.delete(words);
    }

    public void deleteAll(){
        WordRepository.deleteAll();
    }
}
