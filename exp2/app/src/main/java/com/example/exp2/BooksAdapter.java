package com.example.exp2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exp2.dbsqlite.BookDao;
import com.example.exp2.dbsqlite.BookService;
import com.example.exp2.entity.Book;
import com.example.exp2.service.impl.BookServiceImpl;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.LinearViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    //key是书的id，value是book对象
    private static SparseArray<Book> map;
    private static List<Integer> booksId;
    private BookService bookService;

    public static SparseArray<Book> getMap() {
        return map;
    }

    public BooksAdapter(Context context, OnItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;

        //从数据库拿到所有图书
        bookService = new BookService(mContext);
        List<Book> books = bookService.queryAllBooks();
        int size = books.size();
        map = new SparseArray<>(size);
        booksId = new ArrayList<>(size);

        for(Book book : books){
            map.put(book.getId(), book);
            booksId.add(book.getId());
        }
        Log.d("BooksAdapter", "打印所有书籍：" + books);
    }

    @NonNull
    @Override
    public BooksAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Fresco.initialize(viewGroup.getContext());
        return new LinearViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_base_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.LinearViewHolder viewHolder, final int i) {

        Book book = map.get(booksId.get(i));
        viewHolder.bookImage.setImageResource(R.mipmap.book);
        viewHolder.bookDesc.setText(book.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BookIntroActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("bookId", booksId.get(i));
                intent.putExtras(bundle);

                //向详情界面发送bookId，请求返回书名
                ((Activity)mContext).startActivityForResult(intent, HomeActivity.DELETE);
                mListener.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = bookService.getTotalCount();
        System.out.println("共有" + count + "本书");
        return count;
    }

    public static void addBook(Book book){
        booksId.add(book.getId());
        map.put(book.getId(), book);
    }

    public static void deleteBook(Book book){
        booksId.remove(Integer.valueOf(book.getId()));
        map.remove(book.getId());
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private ImageView bookImage;
        private TextView bookDesc;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.iv_book);
            bookDesc = itemView.findViewById(R.id.tv_book);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }
}
