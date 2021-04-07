package com.example.slop_per;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.nio.file.Path;


public class Dloader {
    public String dloader() {
        String result ="Good";
        try{
            URL url = new URL("https://tinyurl.com/38eruapu");
            FileOutputStream out = new FileOutputStream(new File("Vanilla.zip"));
            out.write(url.openStream().read
        }catch (MalformedURLException e){result = "Url address Wrong";}
        catch (IOException e){result ="File System Error";}
        
        return "";
    }

}

//Todo URL url = new URL("https://tinyurl.com/38eruapu");