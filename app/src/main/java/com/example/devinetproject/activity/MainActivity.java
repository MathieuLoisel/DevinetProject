package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.devinetproject.R;
import com.example.devinetproject.bo.Category;
import com.example.devinetproject.bo.Word;
import com.example.devinetproject.vm.CategoryVm;
import com.example.devinetproject.vm.WordVm;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        final WordVm wordVm = new ViewModelProvider(this).get(WordVm.class);
        CategoryVm categoryVm = new ViewModelProvider(this).get(CategoryVm.class);
        Category category = new Category("noms");
        categoryVm.insert(category);

        categoryVm.getByName("noms").observe(this, new Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                Word word = new Word("/img", "guessword", "proposition", category);
                wordVm.insert(word);
            }
        });
    }

    protected boolean onCreateOptions(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}