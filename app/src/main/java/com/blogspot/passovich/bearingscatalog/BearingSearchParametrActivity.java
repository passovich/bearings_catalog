package com.blogspot.passovich.bearingscatalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BearingSearchParametrActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "myLogs";
    Button button1, button2, button3, button4, button5;
    EditText editText1, editText2, editText3, editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bearing_search_parametr);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        button1 = (Button) findViewById(R.id.button1); button1.setOnClickListener(this);
        button1.startAnimation(anim);
        button2 = (Button) findViewById(R.id.button2); button2.setOnClickListener(this);
        button2.startAnimation(anim);
        button3 = (Button) findViewById(R.id.button3); button3.setOnClickListener(this);
        button3.startAnimation(anim);
        button4 = (Button) findViewById(R.id.button4); button4.setOnClickListener(this);
        button4.startAnimation(anim);
        button5 = (Button) findViewById(R.id.button5); button5.setOnClickListener(this);
        button5.startAnimation(anim);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
    }
    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.button1:
                startBearingSearchActivity(0);
                 break;
            case R.id.button2:
                startBearingSearchActivity(1);
                break;
            case R.id.button3:
                startBearingSearchActivity(2);
                break;
            case R.id.button4:
                startBearingSearchActivity(3);
                break;
            case R.id.button5:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyKode, KeyEvent event){
        Intent intent;
        if (keyKode==KeyEvent.KEYCODE_BACK){
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
    public void startBearingSearchActivity(int searchParametr){
        Intent intent;
        intent = new Intent(this,BearingSearchActivity.class);
        intent.putExtra("mark", editText1.getText().toString());
        intent.putExtra("d",    editText2.getText().toString());
        intent.putExtra("D",    editText3.getText().toString());
        intent.putExtra("B",    editText4.getText().toString());
        intent.putExtra("flagOfSearch","true");        //Флаг отображения всей таблицы или результатов выборки
        intent.putExtra("searchParametr", Integer.toString(searchParametr));
        startActivity(intent);
        finish();
    }
}
