package com.example.devinetproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.devinetproject.R;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
    }
}