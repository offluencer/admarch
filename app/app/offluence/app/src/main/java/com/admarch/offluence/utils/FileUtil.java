package com.admarch.offluence.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    private final static String filename = "backup.txt";

    public static void writeToInternalStorage(String data, Context context){
        FileOutputStream fos;

        try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            //default mode is PRIVATE, can be APPEND etc.
            fos.write(data.getBytes());
            fos.close();

//            Toast.makeText(getApplicationContext(),filename + " saved",
//                    Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {e.printStackTrace();}
         catch (IOException e) {
            e.printStackTrace();
        }

    }

}

