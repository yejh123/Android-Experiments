package com.example.contentresolvertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contentresolvertest.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String newId;
    public static final String AUTHORITY = "com.example.exp2.provider";
    public static final String BOOK = "book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加数据
                Uri uri = Uri.parse("content://" + AUTHORITY + "/book");
                ContentValues values = new ContentValues();
//                values.put("name", "A Clash of Kings");
//                values.put("author", "George Martin");
//                values.put("pages", 1040);
//                values.put("price", 55.55);v
                values.put("bname", "钢铁是怎么炼成的");
                values.put("author", "大厚厚的萨");
                values.put("publisher", "飞虎队");

                Uri newUri = getContentResolver().insert(uri, values);
                Log.d(TAG, "addData: " + newUri.getPath());
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查询数据
                Uri uri = Uri.parse("content://" + AUTHORITY + "/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                int count = 0;
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("bname"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        String publisher = cursor.getString(cursor.getColumnIndex("publisher"));
                        String desc = cursor.getString(cursor.getColumnIndex("description"));
                        Log.d("queryData", id + " -- " + name + " -- " + author + " -- " + publisher + " -- " + desc);
                        ++count;
                    }
                    Log.d("queryData", "共有" + count + "本图书");

                    cursor.close();
                }
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newId == null) {
                    Toast.makeText(MainActivity.this, "newId为null", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 更新数据
                Uri uri = Uri.parse("content://" + AUTHORITY + "/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("bname", "A Storm of Swords");
                values.put("author", "发货方大");
                values.put("publisher", "群活动稍差");
                int i =  getContentResolver().update(uri, values, null, null);
                Toast.makeText(MainActivity.this, "修改" + newId + "号图书成功，共更新" + i + "本图书", Toast.LENGTH_SHORT).show();

            }
        });

        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newId == null) {
                    Toast.makeText(MainActivity.this, "newId为null", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 删除数据
                Uri uri = Uri.parse("content://" + AUTHORITY + "/book/" + newId);
                int i = getContentResolver().delete(uri, null, null);
                Toast.makeText(MainActivity.this, "删除" + newId + "号图书成功，共删除" + i + "本图书", Toast.LENGTH_SHORT).show();

                newId = null;
            }
        });
    }

}
