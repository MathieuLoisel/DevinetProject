package com.example.devinetproject.vm;

import android.app.Application;
import android.telephony.SmsManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.dal.CategoryDao;
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
    CategoryRepository categoryRepository;

    public CategoryVm(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepositoryImp(application);
        observer = categoryRepository.get();
    }

    public void insert(Category ... categories){
        categoryRepository.insert(categories);
    }

    public LiveData<List<Category>> get(){
        return null;
    }

    public void update(Category ... categories){
        categoryRepository.update(categories);
    }

    public void delete(Category ... categories){
        categoryRepository.delete(categories);
    }

    public void deleteAll(){
        categoryRepository.deleteAll();
    }


}
