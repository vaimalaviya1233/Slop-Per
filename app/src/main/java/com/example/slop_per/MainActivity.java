package com.example.slop_per;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import android.os.storage.StorageManager;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Enumeration;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity {
    private final String pathTo = "";
    private final Dloader dinit = new Dloader();

    private static final int PERMISSION_STORAGE_CODE = 1000;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check for if permissions are granted
        if (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissions, PERMISSION_STORAGE_CODE);
        }

        findViewById(R.id.starter).setOnClickListener(v -> {//set click listner for button
            try {
                dStarter();//starts download initiater
                Thread.sleep(7000); //halt during file download

            } catch (InterruptedException | IOException e) {
                Toast.makeText(this, String.valueOf(e), Toast.LENGTH_SHORT).show();
            }
        });

//            File ziper = new File("//Folder//Vanilla.zip");
//            TextView txt = findViewById(R.id.path);
//            txt.setText(this.getDataDir().toString());
//            TextView txt2 = findViewById(R.id.pather);
//            txt2.setText(ziper.getAbsolutePath().toString());

    }

/*    private void startDownload() throws IOException, InterruptedException {
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
        dLoader.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        //file creation
        //File cacheFile = new File(this.getCacheDir(), "Vanilla.zip");
        //directory where downloaded file is going to be in __gets current timestamp as file name
        dLoader.setDestinationInExternalFilesDir(this, "//Folder//", "Vanilla.zip");
        TextView tView = findViewById(R.id.path);


        File ziper = new File("//Folder//Vanilla.zip");
        tView.setText(ziper.getAbsolutePath());
        //pathTo = String.valueOf(File.getAbsolutePath());
        //get download service and enque file
        DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        dManager.enqueue(dLoader);
        Thread.sleep(5000);

    }*/

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void clearC() {
        Intent intent = new Intent(StorageManager.ACTION_CLEAR_APP_CACHE);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void dStarter() throws InterruptedException, IOException {
        DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        dinit.initer(this, dManager);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    dStarter();//starts download initiater
                } catch (InterruptedException | IOException e) {
                    Toast.makeText(this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Storage Permission is required to use This App", Toast.LENGTH_LONG).show();
            }
        }
    }
}