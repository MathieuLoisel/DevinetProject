package com.example.devinetproject.bo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Cette classe représnete une catégorie de mot.
 * La catégorie définit un theme regroupant des mots (légumes, fruits, etc...)
 */

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Category() {
    }

    @Ignore
    public Category(String nom) {
        this.name = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", nom='" + name + '\'' +
                '}';
    }
}
