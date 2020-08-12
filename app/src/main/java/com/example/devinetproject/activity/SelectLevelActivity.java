package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ListView;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.adapter.LevelAdapter;
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
                levelList.setAdapter(new LevelAdapter(SelectLevelActivity.this,R.layout.style_ligne_select_level_layout,levels));
            }
        });
    }
}