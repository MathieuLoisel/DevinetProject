package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.adapter.CategoryAdapter;
import com.example.devinetproject.bo.Category;
import com.example.devinetproject.vm.CategoryVm;

import java.util.List;

public class SelectListActivity extends AppCompatActivity {

    private CategoryVm categoryVM = null;
    private ListView categoryList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_list);

        }

    @Override
    protected void onResume() {
        super.onResume();

        categoryList = findViewById(R.id.lv_category_list);
        categoryVM = new ViewModelProvider(this).get(CategoryVm.class);

        categoryVM.get().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryList.setAdapter(new CategoryAdapter(SelectListActivity.this,R.layout.style_ligne_select_list_layout,categories));
            }
        });
    }
}