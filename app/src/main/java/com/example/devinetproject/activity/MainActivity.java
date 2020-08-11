package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

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
        final CategoryVm categoryVm = new ViewModelProvider(this).get(CategoryVm.class);
    }

    protected boolean onCreateOptions(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onClickPlay(View view)
    {
        Intent intent = new Intent(this,PlayActivity.class);
        startActivity(intent);
    }

    public void onClickSubmit(View view) {
        Intent intent = new Intent(this,SubmitActivity.class);
        startActivity(intent);
    }

    public void onClickMyResults(View view) {
        Intent intent = new Intent(this,MyResultsActivity.class);
        startActivity(intent);
    }

    public void onClickQuit(View view) {
        Intent intent = new Intent(this,SubmitActivity.class);
        startActivity(intent);
    }
}