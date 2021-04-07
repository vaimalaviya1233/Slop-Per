package com.example.slop_per;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.*;
import android.os.*;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.widget.Button btnStart = findViewById(com.example.slop_per.R.id.starter);
        btnStart.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

            }
        });
    }
}