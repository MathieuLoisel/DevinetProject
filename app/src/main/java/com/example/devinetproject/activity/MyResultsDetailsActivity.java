package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.devinetproject.R;
import com.example.devinetproject.activity.adapter.LevelAdapter;
import com.example.devinetproject.activity.adapter.LevelAdapter2;
import com.example.devinetproject.activity.adapter.WordAdapter;
import com.example.devinetproject.bo.Level;
import com.example.devinetproject.bo.Word;
import com.example.devinetproject.vm.LevelVm;
import com.example.devinetproject.vm.WordVm;

import java.util.List;

public class MyResultsDetailsActivity extends AppCompatActivity {

    private WordVm wordVM = null;
    private ListView wordList = null;
    private List<Word> words = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_results_details);

        wordList = findViewById(R.id.lv_results_details_words_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        wordVM = new ViewModelProvider(this).get(WordVm.class);

        wordVM.get().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                MyResultsDetailsActivity.this.words = words;
                wordList.setAdapter(new WordAdapter(MyResultsDetailsActivity.this,R.layout.style_ligne_results_details_layout,words));
            }
        });
    }
}