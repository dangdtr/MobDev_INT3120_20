package com.example.slide4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private NumberPicker numberPicker;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout_2);
//        numberPicker = findViewById(R.id.np);
//        numberPicker.setMinValue(0);
//        numberPicker.setMaxValue(999);
//
//        progressBar = findViewById(R.id.progressBar);
//        progressBar.setProgress(20);
    }
}