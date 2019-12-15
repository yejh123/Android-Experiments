package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworld.broadcasttest.BroadcastActivity;
import com.example.helloworld.fragment.FragmentActivity;
import com.example.helloworld.fragmentbestpractice.NewsActivity;
import com.example.helloworld.sample.ListMenuActivity;

public class MainActivity extends Activity {

    private Button btn_intent;
    private Button btn_fragment;
    private Button btn_news;
    private Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_intent = findViewById(R.id.btn_intent);
        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null){
//            actionBar.hide();
//        }

        //fragment
        btn_fragment = findViewById(R.id.btn_fragment);
        btn_fragment.setOnClickListener(new MyIntentClick(this, FragmentActivity.class));

        //news
        btn_news = findViewById(R.id.btn_news);
        btn_news.setOnClickListener(new MyIntentClick(this, NewsActivity.class));

        //broadcast
        Button btn_broadcast = findViewById(R.id.btn_broadcast);
        btn_broadcast.setOnClickListener(new MyIntentClick(this, BroadcastActivity.class));

        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new MyIntentClick(this, TestActivity.class));

        Button btn_list_menu = findViewById(R.id.btn_list_menu);
        btn_list_menu.setOnClickListener(new MyIntentClick(this, ListMenuActivity.class));
    }

    class MyIntentClick implements View.OnClickListener{
        private Context context;
        private Class<?> aClass;

        MyIntentClick(Context context, Class<?> activity){
            this.context = context;
            this.aClass = activity;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, aClass);
            startActivity(intent);
        }
    }

    //==========================创建菜单=============================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;

            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return true;
    }
}
