package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sp;
    boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        bool = sp.getBoolean("log",false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(bool) {
                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(SplashScreen.this,RegPage.class);
                    startActivity(i);
                }
            }
        },1200);
    }
}