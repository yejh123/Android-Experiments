package com.example.helloworld.fragmentbestpractice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_main);
    }
}
