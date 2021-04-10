package com.example.slop_per;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.WatchService;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Enumeration;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    private final String pathTo = "";

    private static final int PERMISSION_STORAGE_CODE = 1000;
    //private static final String DIRECTORY_DOCUMENT = ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissions, PERMISSION_STORAGE_CODE);
        }

        android.widget.Button button = findViewById(R.id.starter);
        button.setOnClickListener(v -> {
            try {
                startDownload();
            } catch (IOException e) {
                Toast.makeText(this, String.valueOf(e), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        //Toast.makeText(this,"Paused",Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //Toast.makeText(this,"Stopped", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onResume() {
        //Toast.makeText(this,"Resumed Activity",Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    startDownload();

                } catch (IOException e) {
                    Toast.makeText(this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Storage Permission is required to use This App", Toast.LENGTH_LONG).show();
            }
        }
    }

    public File getCachePath() {
        return this.getCacheDir();
    }

    private void startDownload() throws IOException {
        //url
        String url = "https://tinyurl.com/38eruapu";
        //Download Manager setup
        DownloadManager.Request dLoader = new DownloadManager.Request(Uri.parse(url));
        //allowed network types
        dLoader.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //notification title set
        dLoader.setTitle("From : Slop-Per");
        //notification description set
        dLoader.setDescription(("Do not stop Download" + "Downloading Resources"));
        //allows mediascanner to scan downloaded file
        dLoader.allowScanningByMediaScanner();
        //notification visibility can not be hidden
        dLoader.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        //file creation
        //File cacheFile = new File(this.getCacheDir(), "Vanilla.zip");
        //directory where downloaded file is going to be in __gets current timestamp as file name
        dLoader.setDestinationInExternalFilesDir(this, "//Folder//", "Vanilla.zip");
        TextView tView = findViewById(R.id.path);
        //File ziper = new File("//Folder//Vanilla.zip");
        //pathTo = String.valueOf(File.getAbsolutePath());

        //get download service and enque file
        DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        dManager.enqueue(dLoader);
    }

    private void extractZip() throws IOException {
        ZipFile Vanilla = new ZipFile(new File("Vanilla.zip"));
        Enumeration<? extends ZipEntry> entries = Vanilla.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            //String nextr =  entries.nextElement();
            File folderw = new File("tkwgter5834");
            InputStream stream = Vanilla.getInputStream(entry);
            FileInputStream inpure = new FileInputStream("Vanilla.zip");
            FileOutputStream outter = new FileOutputStream(new File(folderw + "//" + entry.toString()));
            outter.write(inpure.read());
            outter.close();
        }
    }
}