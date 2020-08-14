package com.example.devinetproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.adapter.ListLevelAdapter;
import com.example.devinetproject.bo.Category;
import com.example.devinetproject.bo.Level;
import com.example.devinetproject.vm.CategoryVm;
import com.example.devinetproject.vm.LevelVm;

import java.util.ArrayList;
import java.util.List;

public class SelectLevelActivity extends AppCompatActivity {

    private ListView levelList = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);

        context = this;
        final CategoryVm categoryVm = new ViewModelProvider(this).get(CategoryVm.class);
        levelList = findViewById(R.id.lv_select_level);
        levelList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Level level = (Level)adapterView.getAdapter().getItem(i);
                final LiveData<List<Category>> listCategories = categoryVm.get();
                listCategories.observe(SelectLevelActivity.this, new Observer<List<Category>>() {
                    @Override
                    public void onChanged(List<Category> categories) {
                        List<String> listName = new ArrayList<>();
                        for (Category cat:categories){
                            listName.add(cat.getName());
                        }
                        Intent redirectToListIntent = new Intent(context, SelectListActivity.class);
                        redirectToListIntent.putStringArrayListExtra("listCategories", (ArrayList)listName);
                        redirectToListIntent.putExtra("idLevel", level.getId());
                        startActivity(redirectToListIntent);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LevelVm levelVm = new ViewModelProvider(this).get(LevelVm.class);
        levelVm.get().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                levelList.setAdapter(new ListLevelAdapter(SelectLevelActivity.this,
                        R.layout.style_ligne_select_level_layout, levels)
                );
            }
        });
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
}