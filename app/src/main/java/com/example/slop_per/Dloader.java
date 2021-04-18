package com.example.slop_per;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class Dloader {
    private void extractZip(Context cnt) throws IOException {
        ZipFile Vanilla = new ZipFile(new File("Vanilla.zip"));
        Enumeration<? extends ZipEntry> entries = Vanilla.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            File folderw = new File("tkwgter5834");
            File folder = cnt.getFilesDir();
            InputStream stream = Vanilla.getInputStream(entry);
            FileInputStream inpure = new FileInputStream("Vanilla.zip");
            FileOutputStream outter = new FileOutputStream(new File(folder + "//" + entry.toString()));
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initer(Context cnt, DownloadManager dManager) throws InterruptedException, IOException {
        startDownload(cnt, dManager);
    }//download starter

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startDownload(Context cnt, @NotNull DownloadManager dManager) throws InterruptedException, IOException {
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
        File tempfile = File.createTempFile("Vanilla", ".zip");
        //dLoader.setDestinationInExternalPublicDir(String.valueOf(cnt.getCacheDir()),String.valueOf(cacheFile));
        dLoader.setDestinationInExternalFilesDir(cnt, "", String.valueOf(tempfile));
        //get download service and enque file
        dManager.enqueue(dLoader);
        Thread.sleep(5000);
        File directory = new File("Data");
        Path zipPath = Paths.get(String.valueOf(tempfile));
        Path extPath = Paths.get(String.valueOf(directory));
        ZipFileUnZip.unzipFolder(zipPath, extPath);
    }
}

/*FileOutputStream out = new FileOutputStream(new File("Vanilla.zip"));
            InputStream inner = url.openStream();
            out.write(inner.read());
            out.close();
            inner.close();*/

//Todo URL url = new URL("https://tinyurl.com/38eruapu");