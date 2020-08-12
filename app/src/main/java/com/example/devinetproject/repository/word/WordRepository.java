package com.example.devinetproject.repository.word;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Word;

import java.util.List;

/**
 * Interface permettant de définir les méthodes utilisables par le repository
 * Contient les mêmes méthodes que dans WordDao
 */

public interface WordRepository {
    void insert(Word... words);
    LiveData<List<Word>> get();
    void update(Word ... words);
    void delete(Word ... words);
    void deleteAll();
    LiveData<List<Word>> getByLevel(int idLevel);
}
