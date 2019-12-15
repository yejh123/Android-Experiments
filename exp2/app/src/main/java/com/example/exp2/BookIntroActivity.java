package com.example.exp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.exp2.dbsqlite.BookService;
import com.example.exp2.entity.Book;
import com.example.exp2.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.xml.transform.Result;

public class BookIntroActivity extends AppCompatActivity implements View.OnClickListener {
    private Book book;
    private SimpleDraweeView img;
    private TextView tv_book_id ;
    private TextView tv_book_name ;
    private TextView tv_book_author ;
    private TextView tv_book_publisher ;
    private TextView tv_book_desc ;
    private Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_intro);
        img = findViewById(R.id.iv_book);
        tv_book_id = findViewById(R.id.tv_book_id);
        tv_book_name = findViewById(R.id.tv_book_name);
        tv_book_author = findViewById(R.id.tv_book_author);
        tv_book_publisher = findViewById(R.id.tv_book_publisher);
        tv_book_desc = findViewById(R.id.tv_book_desc);
        btn_delete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        book = BooksAdapter.getMap().get((int)extras.get("bookId"));
        Log.d("BookIntroActivity", book.toString());

        img.setImageResource(R.mipmap.book);
        tv_book_id.setText("书籍ID：" + book.getId());
        tv_book_name.setText("书名：" + book.getName());
        tv_book_author.setText("作者：" + book.getAuthor());
        tv_book_publisher.setText("出版社：" + book.getPublisher());
        tv_book_desc.setText(book.getDesc());

        btn_delete.setOnClickListener(this);
    }

    //删除图书
    @Override
    public void onClick(View view) {
        String bname = book.getName();
        boolean flag = new BookService(BookIntroActivity.this).deleteBookById(book.getId());
        if(flag){
            ToastUtil.showMsg(BookIntroActivity.this, "成功删除该图书");

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            //更新BooksAdapter
            BooksAdapter.deleteBook(book);
        }else{
            ToastUtil.showMsg(BookIntroActivity.this, "删除失败");
        }

        //返回给HomeActivity书名
        Intent intent = new Intent();
        intent.putExtra("bname", bname);
        setResult(RESULT_OK, intent);
        finish();
    }

}
