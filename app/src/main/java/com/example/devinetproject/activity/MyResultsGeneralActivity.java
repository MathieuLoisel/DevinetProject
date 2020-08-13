package com.example.devinetproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.adapter.GeneralLevelAdapter;
import com.example.devinetproject.bo.Level;
import com.example.devinetproject.vm.LevelVm;

import java.util.List;


public class MyResultsGeneralActivity extends AppCompatActivity {

    private ListView listLevel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_results_general);

        context = this;
        listLevel = findViewById(R.id.listview_levels_progression);

        listLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int listPosition, long adapterPosition) {
                Level level = (Level) adapterView.getAdapter().getItem(listPosition);
                Intent intentRedirectDetailLevel = new Intent(context, MyResultsDetailsActivity.class);
                startActivity(intentRedirectDetailLevel);
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

    @Override
    protected void onResume() {
        super.onResume();

        LevelVm levelVm = new ViewModelProvider(this).get(LevelVm.class);
        LiveData<List<Level>> observer = levelVm.get();

        observer.observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                GeneralLevelAdapter generalLevelAdapter = new GeneralLevelAdapter(MyResultsGeneralActivity.this, R.layout.style_list_results_general, levels);
                listLevel.setAdapter(generalLevelAdapter);
            }
        });
    }


}