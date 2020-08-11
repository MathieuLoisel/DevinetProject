package com.example.devinetproject.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.repository.category.CategoryRepository;
import com.example.devinetproject.repository.category.CategoryRepositoryImp;

import java.util.List;

/**
 * Classe permettant à la vue et au repository de communiquer.
 * Le ViewModel permet à la vue de faire des appel à la BDD (via le wm puis le repo puis la DAL)
 */
public class CategoryVm extends AndroidViewModel {
    /**
     * Permet de n'avoir qu'un seul observateur pour toute l'application
     */
    LiveData<List<Category>> observer;
    LiveData<Category> observerByName;
    LiveData<Category> observerById;
    CategoryRepository CategoryRepository;

    public CategoryVm(@NonNull Application application) {
        super(application);
        CategoryRepository = new CategoryRepositoryImp(application);
        observer = CategoryRepository.get();
    }

    public void insert(Category ... categories){
        CategoryRepository.insert(categories);
    }

    public LiveData<List<Category>> get(){
        return observer;
    }

    public LiveData<Category> getByName(String name){
        observerByName = CategoryRepository.getByName(name);
        return observerByName;
    }

    public LiveData<Category> getById(int id){
        observerById = CategoryRepository.getById(id);
        return observerById;

    }

    public void update(Category ... categories){
        CategoryRepository.update(categories);
    }

    public void delete(Category ... categories){
        CategoryRepository.delete(categories);
    }

    public void deleteAll(){
        CategoryRepository.deleteAll();
    }


}
