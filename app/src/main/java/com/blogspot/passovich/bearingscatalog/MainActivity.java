package com.blogspot.passovich.bearingscatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "myLogs";
    private Button button1,button2,button3;
    private HelperMethodClass helperMethodClass = new HelperMethodClass();
    private String nameDB = "bearings.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        button1 = (Button) findViewById(R.id.button1); button1.setOnClickListener(this);
        button1.startAnimation(anim);
        button2 = (Button) findViewById(R.id.button2); button2.setOnClickListener(this);
        button2.startAnimation(anim);
        button3 = (Button) findViewById(R.id.button3); button3.setOnClickListener(this);
        button3.startAnimation(anim);

        helperMethodClass.checkDBVersion(this,nameDB);
    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.button1:
                intent = new Intent(this,SectionsMenuActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button2:
                intent = new Intent(this,BearingSearchParametrActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button3:
                finish();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyKode, KeyEvent event){
        if (keyKode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return true;
    }
}
