package com.blogspot.passovich.bearingscatalog;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class File {

    private static final String TAG = "myLogs";
    public static void createFile (Context context,String fileName){
        try{
            FileOutputStream outputStream = context.openFileOutput(fileName,context.MODE_PRIVATE);
            outputStream.close();
        }catch (Exception e){e.printStackTrace();}
    }
    public static void saveFile (Context context,String fileName,String information){
        try{
            FileOutputStream outputStream = context.openFileOutput(fileName,context.MODE_PRIVATE);
            outputStream.write(information.getBytes());
            outputStream.close();
        }catch (Exception e){e.printStackTrace();}
    }
    public static String readFile (Context context,String fileName){
        String ret="";
        try{
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream!=null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String receiveString="";
                while((receiveString=bufferedReader.readLine())!=null){
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret=stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e){Log.e(TAG,"File not found"+e.toString());ret="fileNotFound";}
        catch (IOException e){Log.e(TAG,"Can't read file"+e.toString());}
        Log.d(TAG,"Чтение файла "+fileName);
        Log.e(TAG,"ret="+ret);
        return ret;
    }
    public static void deleteMyFile (Context context,String fileName){
        context.deleteFile(fileName);
    }
}
