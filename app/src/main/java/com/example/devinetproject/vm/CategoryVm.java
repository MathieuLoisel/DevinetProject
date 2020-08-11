package com.example.devinetproject.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.bo.CategoryWithWords;
import com.example.devinetproject.repository.category.ICategoryRepository;
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
    LiveData<Category> observeByName;
    ICategoryRepository ICategoryRepository;

    public CategoryVm(@NonNull Application application) {
        super(application);
        ICategoryRepository = new CategoryRepositoryImp(application);
        observer = ICategoryRepository.get();
    }

    public void insert(Category ... categories){
        ICategoryRepository.insert(categories);
    }

    public LiveData<List<Category>> get(){
        return observer;
    }

    public LiveData<List<CategoryWithWords>> getCategoryWithWords(){
        return ICategoryRepository.getCategoryWithWords();
    }

    public LiveData<Category> getByName(String name){
        observeByName = ICategoryRepository.getByName(name);
        return observeByName;
    }

    public void update(Category ... categories){
        ICategoryRepository.update(categories);
    }

    public void delete(Category ... categories){
        ICategoryRepository.delete(categories);
    }

    public void deleteAll(){
        ICategoryRepository.deleteAll();
    }


}
