package com.example.slop_per;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static android.content.Context.DOWNLOAD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;

class Dloader {
    private void extractZip() throws IOException {
        ZipFile Vanilla = new ZipFile(new File("Vanilla.zip"));
        Enumeration<? extends ZipEntry> entries = Vanilla.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            File folderw = new File("tkwgter5834");
            InputStream stream = Vanilla.getInputStream(entry);
            FileInputStream inpure = new FileInputStream("Vanilla.zip");
            FileOutputStream outter = new FileOutputStream(new File(folderw + "//" + entry.toString()));
            outter.write(inpure.read());
            outter.close();
        }
    }

    public File getCachePath(@org.jetbrains.annotations.NotNull Context cnt) {
        return cnt.getCacheDir();
    }//cache directory

    public File getDataPath(@org.jetbrains.annotations.NotNull Context cnt) {
        return cnt.getDataDir();
    }//data directory

    public void initer(Context cnt, DownloadManager dManager) throws InterruptedException {
        startDownload(cnt, dManager);
    }//download starter

    private void startDownload(Context cnt, @NotNull DownloadManager dManager) throws InterruptedException {
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
        //file creation
        File cacheFile = new File("Vanilla.zip");
        //directory where downloaded file is going to be in __gets current timestamp as file name
        //dLoader.setDestinationInExternalPublicDir(String.valueOf(cnt.getCacheDir()),String.valueOf(cacheFile));
        dLoader.setDestinationInExternalFilesDir(cnt, "", String.valueOf(cacheFile));


//        File ziper = new File("//Folder//Vanilla.zip");
//        tView.setText(ziper.getAbsolutePath());
        //pathTo = String.valueOf(File.getAbsolutePath());
        //get download service and enque file
        dManager.enqueue(dLoader);
        Thread.sleep(5000);

    }
}

/*FileOutputStream out = new FileOutputStream(new File("Vanilla.zip"));
            InputStream inner = url.openStream();
            out.write(inner.read());
            out.close();
            inner.close();*/

//Todo URL url = new URL("https://tinyurl.com/38eruapu");