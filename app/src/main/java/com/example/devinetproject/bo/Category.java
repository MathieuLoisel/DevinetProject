package com.example.devinetproject.bo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Cette classe représnete une catégorie de mot.
 * La catégorie défini la taille du mot (mot de 4 lettres, mot de 5 lettres, etc...)
 */

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;

    public Category(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
