package com.example.devinetproject.bo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Cette classe représente les différents niveaux de difficulté (4 lettres, 5 lettres, etc...)
 */
@Entity
public class Level {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Level(String name) {
        this.name = name;
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
        return "Level{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
