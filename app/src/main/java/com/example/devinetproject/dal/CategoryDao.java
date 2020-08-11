package com.example.devinetproject.dal;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.bo.CategoryWithWords;
import com.example.devinetproject.bo.Word;

import java.util.List;

/**
 * Dao permettant de définir les fonctions d'accès à la BDD.
 * Utilisation de Room en tant qu'ORM.
 * Nous définissions les méthodes d'appel à la base de données et Room se chargera de les remplir
 */

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category... categories);

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> get();

    @Transaction
    @Query("SELECT * FROM Category")
    LiveData<List<CategoryWithWords>> getCategoryWithWords();

    @Query("SELECT * FROM Category WHERE name LIKE :name")
    LiveData<Category> getByName(String name);

    @Update
    void update(Category ... categories);

    @Delete
    void delete(Category ... categories);

    @Query("DELETE FROM Category")
    void deleteAll();
}
