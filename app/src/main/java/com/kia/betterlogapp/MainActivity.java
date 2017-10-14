package com.kia.betterlogapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kia.betterlog.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("I am a label", "I am a message");
    }
}
