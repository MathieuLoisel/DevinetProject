package com.example.devinetproject.bo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithWords {
    @Embedded Category category;
    @Relation(
            parentColumn = "idCategory",
            entityColumn = "idExcel"
    )
    public List<Word> words;
}
