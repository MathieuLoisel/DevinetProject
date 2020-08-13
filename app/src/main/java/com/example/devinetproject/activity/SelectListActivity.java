package com.example.devinetproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.btn_home).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btn_main_settings :
                Intent intentSettings = new Intent(this,SettingsActivity.class);
                startActivity(intentSettings);
                return true;
            case R.id.btn_main_about_us :
                Intent intentAboutUs = new Intent(this,AboutUsActivity.class);
                startActivity(intentAboutUs);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                categoryList.setAdapter(new CategoryAdapter(SelectListActivity.this,R.layout.style_ligne_select_list_layout,categories));
            }
        });
    }
}