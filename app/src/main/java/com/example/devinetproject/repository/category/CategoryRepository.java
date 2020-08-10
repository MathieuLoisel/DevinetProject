package com.example.devinetproject.repository.category;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Category;

import java.util.List;

public interface CategoryRepository {
    void insert(Category ... categories);
    LiveData<List<Category>> get();
    void update(Category ... categories);
    void delete(Category ... categories);
    void deleteAll();
}
