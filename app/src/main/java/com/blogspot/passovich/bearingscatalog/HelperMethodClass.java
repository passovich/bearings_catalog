package com.blogspot.passovich.bearingscatalog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HelperMethodClass {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private static String TAG ="myLogs";
    private final int ACTUAL_DB_VERSION = 8;

    void checkDBVersion(Context context, String nameDB){
        String str = File.readFile(context,"dbVersion");
        Log.d(TAG,"Data Base version = " + str);
        if (str.equals("fileNotFound")){
            //Если первый запуск приложения, то создать файл с текущей версией базы и перезаписать базу
            onCreateDBAPK(context,nameDB);
            File.saveFile(context,"dbVersion",Integer.toString(ACTUAL_DB_VERSION));
        } else{
            //Иначе проверить версию в файле, если не совпадает с текущей, перезаписать базу
            if (Integer.parseInt(str)!=ACTUAL_DB_VERSION){
                onCreateDBAPK(context,nameDB);
                File.saveFile(context,"dbVersion",""+ACTUAL_DB_VERSION);
            }
        }
    }

    void onCreateDBAPK(Context context, String nameDB){
        Log.d(TAG,"onCreateDB");
        dbHelper = new DBHelper(context, nameDB);
        try {dbHelper.createDataBase();}
        catch(IOException ioe){throw new Error("Unable to create DataBase");}
        try{dbHelper.openDataBase();}
        catch (SQLException sqle){throw sqle;}
        dbHelper.close();
    }
    //метод возвращает результаты поиска в таблицах
    boolean onViewSearchResult (
            Context context,
            String nameDB,          //имя базы
            String nameTableDB,     //имя таблицы
            int tekString,          //текущая строка
            String [] strResult,
            String [] columns,
            String selectionColumn,
            String [] selectionArgs
    ){
        openDB(context,nameDB);
        cursor=database.query(nameTableDB,null,selectionColumn,selectionArgs,null,null,null);
        if(cursor.moveToPosition(tekString)){
            strResult[0] = cursor.getString(cursor.getColumnIndex(columns[0]));
            strResult[1] = cursor.getString(cursor.getColumnIndex(columns[1]));
            strResult[2] = cursor.getString(cursor.getColumnIndex(columns[2]));
            strResult[3] = cursor.getString(cursor.getColumnIndex(columns[3]));
         }
        else{cursor.close();dbHelper.close();database.close();return false;}
        cursor.close();dbHelper.close();database.close();
        return true;
    }
    //метод вывода заголовков таблиц в списке таблиц
    boolean onViewSections (
            Context context,
            String nameDB,
            String nameTableDB,
            int tekString,
            String [] item,
            String selection
    ){
        openDB(context, nameDB);
        cursor=database.query(nameTableDB,null,null,null,null,null,null);
        if(cursor.moveToPosition(tekString)){
            item[0] = cursor.getString(cursor.getColumnIndex(selection));
        }
        else{cursor.close(); dbHelper.close(); database.close(); return false;}
        cursor.close(); dbHelper.close(); database.close();
        return true;
    }
   //Чтение и вывод таблицы из базы данных
   void getListView (
           Context context,
           String nameDB,
           String tableName,
           String[]columns,
           String []headerColumns,
           ListView listView
   ){
        openDB(context,nameDB);
        Cursor cursor=database.query(tableName,columns,null,null,null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
            context,
            R.layout.my_list,
            cursor,
            headerColumns,
            new int[]{R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4}
        );
            listView.setAdapter(adapter);
   }
   public ArrayList<HashMap<String,Object>> getSectionList(
           Context context,
           String nameDB,
           String selection,
           String sectionName,
           String myImage
   ){
        ArrayList<HashMap<String,Object>> sections;
        openDB(context,nameDB);
        cursor=database.query("sections",null,null,null,null,null,null);
        //Создаём коллекцию с разделами и картинками
        sections  =new ArrayList<HashMap<String,Object>>();    //создаём коллекцию списков
        HashMap<String, Object>hm;
        DBConnection DBC = new DBConnection();
        boolean flag = true;
        int counter = 0;
        while (flag){                                          //Заполнение коллекции ИЗ БД
            hm = new HashMap<String,Object>();
            String item[]={"nuuul"};
            flag = onViewSections(
                   context,
                   nameDB,
                   "sections",
                   counter,
                   item,
                   selection);
           if (!flag) continue;
           hm.put(sectionName, item[0]);
           hm.put(myImage, DBC.getIcon(counter));
           sections.add(hm);
           counter++;
           Log.d(TAG,"item=" + item[0]);
        }
        cursor.close();dbHelper.close();database.close();
        return sections;
   }
    ArrayList<HashMap<String, Object>> getSearhResultList(
            Context context,
            String nameDB,
            int searchParametr,
            String searchColumns[],
            String selectionArg[],
            String columns[],
            String myImage
    ){
        DBConnection DBC = new DBConnection();
        //создаём массив списков
        ArrayList<HashMap<String, Object>> sections = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hm;
        boolean flag = true;
        int counter = 0;
        for (int i = 0; i < DBC.TABLE_NUMBER; i++) {//проверяем по очереди все таблицы
            DBC.getSearchParametrsColumns(i, searchColumns);
            while (flag) {
                //Заполняем коллекцию ИЗ БД
                hm = new HashMap<String, Object>();
                DBC.getSearchColumns(i,columns);
                String strResult[] = new String[4];
                flag = onViewSearchResult(
                        context,
                        nameDB,
                        "bearings_" + i,
                        counter,
                        strResult,
                        columns,
                        searchColumns[searchParametr],
                        selectionArg
                );
                if (!flag) continue;
                hm.put("mark", strResult[0]);
                hm.put("d", strResult[1]);
                hm.put("D", strResult[2]);
                hm.put("B", strResult[3]);
                hm.put(myImage, DBC.getIcon(i));
                sections.add(hm);
                counter++;
            }
            counter = 0; flag = true;
        }
        return sections;
    }
   private void openDB(Context context,String nameDB){
       //////-------Открываем базу ------//////////
       dbHelper = new DBHelper(context,nameDB);
       try {database = dbHelper.getWritableDatabase();}
       catch (SQLiteException e){database = dbHelper.getReadableDatabase();}
   }
}