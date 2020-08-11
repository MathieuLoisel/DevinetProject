package com.example.devinetproject.bo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Cette classe représnete une catégorie de mot.
 * La catégorie défini la taille du mot (mot de 4 lettres, mot de 5 lettres, etc...)
 */

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int idCategory;
    private String name;
    private int idExcel;

    public Category() {
    }

    @Ignore
    public Category(String nom) {
        this.name = nom;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + idCategory +
                ", nom='" + name + '\'' +
                '}';
    }
}
