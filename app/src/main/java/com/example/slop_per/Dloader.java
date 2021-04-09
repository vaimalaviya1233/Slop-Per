package com.example.slop_per;

import android.app.DownloadManager;
import android.widget.Toast;

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

import static android.content.Context.DOWNLOAD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

class Dloader {
    public int dloader() {
        int result = 1;
        try {
            URL url = new URL("https://tinyurl.com/38eruapu");


        } catch (MalformedURLException e) {
            result = 0;
        }
        return result;
    }

    private String download(android.net.Uri uri, String toStorage) {
        String result = " Status Fine ";
        //DownloadManager dmanager =(DownloadManager)getSystemService(this,DOWNLOAD_SERVICE);

        return result;
    }
}

/*FileOutputStream out = new FileOutputStream(new File("Vanilla.zip"));
            InputStream inner = url.openStream();
            out.write(inner.read());
            out.close();
            inner.close();*/

//Todo URL url = new URL("https://tinyurl.com/38eruapu");