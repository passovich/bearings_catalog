package com.blogspot.passovich.bearingscatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class BearingSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "myLogs";

    private Button button1;
    private TextView textView1, textView2, textView3, textView4;
    private ListView listView;

    private String searchColumns[] = new String[4];         //запрос пользователя, получаем из предыдущего активити
    private String selectionArg[] = {""};                   //параметр запроса на поиск для выборки в cursor
    private String columns[] = new String[4];               //заголовки столбцов текущей таблицы
    private int searchParameter;
    private ArrayList<HashMap<String, Object>> sections;    //Итоговая коллекция для вывода результатов поиска
    private HelperMethodClass helperMethodClass = new HelperMethodClass();
    private String nameDB = "bearings.db";
    private String myImage = "myImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bearing_search);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        button1 = (Button) findViewById(R.id.button1); button1.setOnClickListener(this);
        button1.startAnimation(anim);
        textView1 = (TextView) findViewById(R.id.textView1); textView1.setOnClickListener(this);
        textView2 = (TextView) findViewById(R.id.textView2); textView2.setOnClickListener(this);
        textView3 = (TextView) findViewById(R.id.textView3); textView3.setOnClickListener(this);
        textView4 = (TextView) findViewById(R.id.textView4); textView4.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        //получаем номер столбца для поиска
        searchParameter = Integer.parseInt(intent.getStringExtra("searchParametr"));
        //получаем строку для поиска в столбце
        switch (searchParameter){
            case 0:selectionArg[0] = intent.getStringExtra("mark"); break;
            case 1:selectionArg[0] = intent.getStringExtra("d"); break;
            case 2:selectionArg[0] = intent.getStringExtra("D"); break;
            case 3:selectionArg[0] = intent.getStringExtra("B"); break;
        }
        //получаем коллекцию с результатами поиска
        sections = helperMethodClass.getSearhResultList(
                this,
                nameDB,
                searchParameter,
                searchColumns,
                selectionArg,
                columns,
                myImage
        );
        //отправляем коллекцию в адаптер
        SimpleAdapter adapter_image = new SimpleAdapter(
                this
                ,sections
                ,R.layout.my_list_search_result_image
                ,new String[]{"mark", "d", "D", "B", myImage}//Массив названий
                ,new int[]{R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.img} //Массив Форм
        );
        listView.setAdapter(adapter_image);

    }
    @Override
    public void onClick(View view) {
        Intent intent;
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        switch (view.getId()){
            case R.id.button1:
                button1.startAnimation(anim);
                intent = new Intent(this, BearingSearchParametrActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyKode, KeyEvent event){
        Intent intent;
        if (keyKode==KeyEvent.KEYCODE_BACK){
            intent = new Intent(this, BearingSearchParametrActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
