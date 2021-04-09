package com.example.slop_per;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.pm.PackageManager;
import android.*;
import android.os.*;

import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.widget.Button button = findViewById(R.id.starter);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                /*if (checkSelfPermission(com.example.slop_per.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] permisissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                } else {//granted permissions
                    startDownload();
                }*/
            }
        });
    }

    private void startDownload() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {

    }
}