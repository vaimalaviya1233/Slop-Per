package com.example.slop_per;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentSender;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.Manifest.permission;
import android.*;
import android.os.*;

import androidx.annotation.NonNull;

import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.net.URL;

import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;
    //private static final String DIRECTORY_DOCUMENT = ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.widget.Button button = findViewById(R.id.starter);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] permisissions;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                        permisissions = new String[]{MANAGE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
                    } else {
                        permisissions = new String[]{permission.READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
                    }
                    requestPermissions(permisissions, PERMISSION_STORAGE_CODE);
                } else {//granted permissions
                    startDownload();
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startDownload();
            } else {
                Toast.makeText(this, "Storage Permission is required to use This App", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startDownload() {
        //url
        String url = "https://tinyurl.com/38eruapu";
        //Download Manager setup
        DownloadManager.Request dLoader = new DownloadManager.Request(Uri.parse(url));
        //allowed network types
        dLoader.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //notification title set
        dLoader.setTitle((R.string.app_name + " - Don't Cancle"));
        //notification description set
        dLoader.setDescription(("Do not stop Download" + "Downloading Resources"));
        //allows mediascanner to scan downloaded file
        dLoader.allowScanningByMediaScanner();
        //notification visibility is hidden
        dLoader.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        //directory where downloaded file is going to be in __gets current timestamp as file name
        dLoader.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, "Slop-per.zip");

        //get download service and enque file
        DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        dManager.enqueue(dLoader);

    }
}