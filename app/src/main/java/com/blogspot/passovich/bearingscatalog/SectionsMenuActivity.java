package com.blogspot.passovich.bearingscatalog;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SectionsMenuActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private final String TAG = "myLogs";

    private Button button1;
    private ArrayList<HashMap<String,Object>> sections;
    private ListView listView;

    private HelperMethodClass helperMethodClass = new HelperMethodClass();
    private String nameDB = "bearings.db";
    private String selection = "section";
    private String sectionName = "Section";
    private String myImage = "myImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections_menu);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        listView = (ListView) findViewById(R.id.listView);
        button1 = (Button) findViewById(R.id.button1);button1.setOnClickListener(this);
        button1.startAnimation(anim);

        sections = helperMethodClass.getSectionList(
                this,
                nameDB,
                selection,
                sectionName,
                myImage
        );
        SimpleAdapter adapter_image = new SimpleAdapter(
                this
                ,sections
                ,R.layout.my_list_image
                ,new String[]{sectionName,myImage}       //Массив названий
                ,new int[]{R.id.textView1,R.id.img}      //Массив Форм
        );
        listView.setAdapter(adapter_image);
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView <?> arg0, View view, int position,long id){
        Intent intent;
        intent = new Intent(this,SectionViewActivity.class);
        intent.putExtra("id",Integer.toString(position));
        intent.putExtra("flagOfSearch","false");            //Флаг отображения всей таблицы или результатов выборки
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyKode, KeyEvent event){
        Intent intent;
        if (keyKode==KeyEvent.KEYCODE_BACK){
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}

