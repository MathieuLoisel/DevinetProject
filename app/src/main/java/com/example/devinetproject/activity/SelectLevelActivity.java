package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.adapter.LevelAdapter2;
import com.example.devinetproject.bo.Level;
import com.example.devinetproject.vm.LevelVm;

import java.util.List;

public class SelectLevelActivity extends AppCompatActivity {

    private LevelVm levelVM = null;
    private ListView levelList = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        levelList = findViewById(R.id.lv_select_level);
        context=this;

        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int listPosition, long adapterPosition) {
                Level currentLevel = (Level) adapterView.getAdapter().getItem(listPosition);
                Intent intentRedirectToOneLevel = new Intent(context, SelectListActivity.class);
                intentRedirectToOneLevel.putExtra("Level", currentLevel);

                startActivity(intentRedirectToOneLevel);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        levelVM = new ViewModelProvider(this).get(LevelVm.class);

        Log.i("test", "coucou");
        levelVM.get().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                levelList.setAdapter(new LevelAdapter2(SelectLevelActivity.this,R.layout.style_ligne_select_level_layout,levels));
            }
        });
    }
}