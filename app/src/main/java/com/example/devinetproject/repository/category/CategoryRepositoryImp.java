package com.example.devinetproject.repository.category;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.dal.AppDatabase;
import com.example.devinetproject.dal.CategoryDao;

import java.util.List;

public class CategoryRepositoryImp implements CategoryRepository{

    AppDatabase bdd;
    CategoryDao categoryDao;

    public CategoryRepositoryImp(Application application) {
        bdd = AppDatabase.getInstance(application);
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
