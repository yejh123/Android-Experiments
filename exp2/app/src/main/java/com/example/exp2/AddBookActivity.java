package com.example.exp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.exp2.R;
import com.example.exp2.dbsqlite.BookDao;
import com.example.exp2.dbsqlite.BookService;
import com.example.exp2.entity.Book;
import com.example.exp2.util.ToastUtil;

import java.util.Map;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_id;
    private EditText et_bname;
    private EditText et_author;
    private EditText et_publisher;
    private EditText et_description;
    private Button btn_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        et_id = findViewById(R.id.et_id);
        et_bname = findViewById(R.id.et_bname);
        et_author = findViewById(R.id.et_author);
        et_publisher = findViewById(R.id.et_publisher);
        et_description = findViewById(R.id.et_description);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = Integer.parseInt(et_id.getText().toString());
        String bname = et_bname.getText().toString();
        String author = et_author.getText().toString();
        String publisher = et_publisher.getText().toString();
        String desc = et_description.getText().toString();
        Book book = new Book(id, bname, author, publisher, desc);
        //添加到数据库中
        BookService bookService = new BookService(AddBookActivity.this);
        boolean flag = bookService.addBook(book);

        if(flag){
            Log.d("AddBookActivity", "增加书籍：" + book);
            //更新BooksAdapter
            BooksAdapter.addBook(book);

            //添加完图书后自动跳转
            Intent intent = new Intent(AddBookActivity.this, HomeActivity.class);
            intent.putExtra("bname", bname);
            setResult(RESULT_OK, intent);
            finish();
        }else{
            ToastUtil.showMsg(AddBookActivity.this, "当前图书ID已存在，请重新设置图书ID");
        }

    }
}
