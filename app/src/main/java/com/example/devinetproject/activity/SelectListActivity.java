package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.adapter.CategoryAdapter;
import com.example.devinetproject.bo.Category;
import com.example.devinetproject.repository.category.CategoryRepository;
import com.example.devinetproject.repository.category.CategoryRepositoryImp;
import com.example.devinetproject.vm.CategoryVm;

import java.util.List;

public class SelectListActivity extends AppCompatActivity {

    private CategoryVm categoryVM = null;
    private ListView categoryList = null;
    private List<Category> categories = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_list);

        }

    @Override
    protected void onResume() {
        super.onResume();

        categoryList = findViewById(R.id.lv_category_list);
        categoryVM = ViewModelProviders.of(this).get(CategoryVm.class);

        categoryVM.get().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                SelectListActivity.this.categories = categories;
                categoryList.setAdapter(new CategoryAdapter(SelectListActivity.this,R.layout.style_ligne_select_liste_layout,categories));
            }
        });
    }
}