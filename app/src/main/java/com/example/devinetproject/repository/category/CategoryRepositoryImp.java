package com.example.devinetproject.repository.category;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.dal.AppDatabase;
import com.example.devinetproject.dal.CategoryDao;

import java.util.List;

public class CategoryRepositoryImp implements CategoryRepository {

    CategoryDao categoryDao;

    public CategoryRepositoryImp(Context context) {
        AppDatabase bdd = AppDatabase.getInstance(context);
        categoryDao = bdd.getCategoryDao();
    }

    @Override
    public void insert(final Category... categories) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
                @Override
                public void run() {
                categoryDao.insert(categories);
            }
        });
    }

    @Override
    public LiveData<List<Category>> get() {
        return categoryDao.get();
    }

    @Override
    public LiveData<Category> getByName(String name) {
        return categoryDao.getByName(name);
    }

    @Override
    public LiveData<Category> getById(int id) {
        return categoryDao.getById(id);
    }

    @Override
    public void update(final Category... categories) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.update(categories);
            }
        });

    }

    @Override
    public void delete(final Category... categories) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.delete(categories);
            }
        });
    }

    @Override
    public void deleteAll() {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.deleteAll();
            }
        });
    }
}
