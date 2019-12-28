package com.blogspot.passovich.bearingscatalog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class SectionViewActivity extends Activity implements View.OnClickListener {
    private final String TAG = "SectionViewLogs";
    private Intent intent;
    private Button button2;
    private ImageView image;
    private ListView listView;
    private String nameDB = "bearings.db";
    private String []columns=new String[5];
    private String []headerColumns = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_view);
        button2 = (Button) findViewById(R.id.button2);button2.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("id"));
        HelperMethodClass HPC = new HelperMethodClass();
        DBConnection DBC = new DBConnection();
        DBC.getViewColumns(id,columns);
        DBC.getSearchColumns(id,headerColumns);
        //Флаг отображения всей таблицы или результатов выборки
        boolean flagOfSearch = Boolean.parseBoolean(intent.getStringExtra("flagOfSearch"));
        //Выводим всю таблицу
        if(!flagOfSearch) {
            image = (ImageView) findViewById(R.id.img);
            image.setImageResource(DBC.getSmallDrawings(id));
            DBC.getViewColumns(id ,columns);
            HPC.getListView(this, nameDB,"bearings_" + id, columns, headerColumns, listView );
        }
    }
    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.button2:
                intent = new Intent(this, SectionsMenuActivity.class);
                startActivity(intent);
                finish();
            break;
        }
    }
    @Override
    public boolean onKeyDown(int keyKode, KeyEvent event){
        Intent intent;
        if (keyKode == KeyEvent.KEYCODE_BACK){
            intent = new Intent(this, SectionsMenuActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
