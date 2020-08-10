package com.example.devinetproject.repository.category;

import com.example.devinetproject.bo.Category;

import java.util.List;

public interface CategoryRepository {
    void insert(Category ... categories);
    List<Category> get();
    void update(Category ... categories);
    void delete(Category ... categories);
    void deleteAll();
}
