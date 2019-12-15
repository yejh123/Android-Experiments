package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        // 步骤1：创建DatabaseHelper对象
        // 注：此时还未创建数据库
        SQLiteOpenHelper dbHelper = new DatabaseHelper(SQLiteActivity.this, "test_carson");

        // 步骤2：真正创建 / 打开数据库
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase(); // 创建 or 打开 可读/写的数据库
        SQLiteDatabase sqliteDatabase2 = dbHelper.getReadableDatabase(); // 创建 or 打开 可读的数据库

    }
}
