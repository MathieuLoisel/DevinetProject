package com.example.devinetproject.dal;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.devinetproject.bo.Word;

import java.util.List;

/**
 * Dao permettant de définir les fonctions d'accès à la BDD.
 * Utilisation de Room en tant qu'ORM.
 * Nous définissions les méthodes d'appel à la base de données et Room se chargera de les remplir
 */

@Dao
public interface WordDao {
    @Insert
    void insert(Word ... words);

    @Query("SELECT * FROM Word")
    LiveData<List<Word>> get();

    @Update
    void update(Word ... words);

    @Delete
    void delete(Word ... words);

    @Query("DELETE FROM Word")
    void deleteAll();
}
