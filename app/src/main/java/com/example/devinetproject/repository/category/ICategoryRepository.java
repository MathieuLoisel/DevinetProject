package com.example.devinetproject.repository.category;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.bo.CategoryWithWords;

import java.util.List;

public interface ICategoryRepository {
    void insert(Category ... categories);
    LiveData<List<Category>> get();
    LiveData<List<CategoryWithWords>> getCategoryWithWords();
    LiveData<Category> getByName(String name);
    void update(Category ... categories);
    void delete(Category ... categories);
    void deleteAll();
}
