package com.kia.betterlogapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kia.betterlog.Log;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void writeDummyLog(View view) {
        Log.setTag("AN OPTIONAL TAG");
        Log.d("I am a label", "I am debug message");
        Log.e(Arrays.asList("label1", "label2"), "I am error message.");
        Log.w("I am warning message.");
        Log.i("Info!!", "I am info message");

    }
}
