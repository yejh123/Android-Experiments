package com.example.exp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.exp2.util.ToastUtil;

public class HomeActivity extends AppCompatActivity {
    public  static final int DELETE = 0;
    public  static final int ADD = 1;

    private RecyclerView rv_books;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv_books = findViewById(R.id.rv_books);
        btn_add = findViewById(R.id.btn_add);

        rv_books.setAdapter(new BooksAdapter(HomeActivity.this, new BooksAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(HomeActivity.this, "正在前往第" + pos + "本书的详细介绍...", Toast.LENGTH_SHORT).show();
            }
        }));

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddBookActivity.class);
                startActivityForResult(intent, HomeActivity.ADD);
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        rv_books.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case HomeActivity.DELETE:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("bname");
                    ToastUtil.showMsg(HomeActivity.this, "图书《" + returnedData + "》已成功删除");
                }
                break;
            case HomeActivity.ADD:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("bname");
                    ToastUtil.showMsg(HomeActivity.this, "图书《" + returnedData + "》已成功添加");
                }
                break;
        }
    }
}
