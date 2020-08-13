package com.example.devinetproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.adapter.LevelAdapter;
import com.example.devinetproject.activity.adapter.LevelAdapter2;
import com.example.devinetproject.bo.Level;
import com.example.devinetproject.vm.LevelVm;

import java.util.List;

public class SelectLevelActivity extends AppCompatActivity {

    private LevelVm levelVM = null;
    private ListView levelList = null;
    private List<Level> levels = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

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

        levelList = findViewById(R.id.lv_select_level);
        levelVM = ViewModelProviders.of(this).get(LevelVm.class);

        levelVM.get().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                SelectLevelActivity.this.levels = levels;
                levelList.setAdapter(new LevelAdapter2(SelectLevelActivity.this,R.layout.style_ligne_select_level_layout,levels));
            }
        });
    }
}