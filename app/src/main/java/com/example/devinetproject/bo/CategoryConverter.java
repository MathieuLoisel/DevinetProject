package com.example.devinetproject.bo;

import androidx.room.TypeConverter;

public class CategoryConverter {

    @TypeConverter
    public static int fromCategory(Category category){
        return category.getIdCategory();
    }

}
