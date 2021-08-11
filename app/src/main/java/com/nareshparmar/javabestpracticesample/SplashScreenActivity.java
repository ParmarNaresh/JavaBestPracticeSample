package com.nareshparmar.javabestpracticesample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initAllControls();
    }

    private void initAllControls()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },20);
    }
}