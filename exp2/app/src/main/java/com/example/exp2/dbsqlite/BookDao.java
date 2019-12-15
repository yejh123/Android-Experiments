package com.example.exp2.dbsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exp2.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private Context context;

    public BookDao(Context context) {
        this.context = context;
    }

    public void addBook(Book book){
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(context, "library", MySQLiteOpenHelper.Version);
        SQLiteDatabase db =dbHelper.getReadableDatabase();

        String sql = "insert into book values(?,?,?,?,?)";
        db.execSQL(sql, new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPublisher()});
    }

    public void deleteBookById(int bookId){
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(context, "library", MySQLiteOpenHelper.Version);
        SQLiteDatabase db =dbHelper.getReadableDatabase();

        String sql = "delete from book where id=?";
        db.execSQL(sql, new Object[]{bookId});
    }

    public void updateBookById(int bookId, Book book){
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(context, "library", 1);
        SQLiteDatabase db =dbHelper.getReadableDatabase();

        String sql = "update book set bname=?, author=?, publisher=?, description=? where id=?;";
        db.execSQL(sql, new Object[]{book.getName(), book.getAuthor(), book.getPublisher(), book.getDesc(), bookId});
    }

    public Book queryBookById(int bookId) {
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(context, "library", MySQLiteOpenHelper.Version);
        SQLiteDatabase db =dbHelper.getReadableDatabase();

        String sql = "select id, bname, author, publisher, description from book where id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(bookId)});

        Book book = null;
        if(cursor.moveToFirst()){
            int id = cursor.getInt(0);
            String bname = cursor.getString(1);
            String author = cursor.getString(2);
            String publisher = cursor.getString(3);
            String description = cursor.getString(4);
            book = new Book(id, bname, author, publisher, description);
        }
        cursor.close();
        return book;
    }

    public boolean isExisted(int bookId){
        return queryBookById(bookId) != null;
    }

    public int getTotalCount(){
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(context, "library", MySQLiteOpenHelper.Version);
        SQLiteDatabase db =dbHelper.getReadableDatabase();

        String sql = "select count(*) from book";
        Cursor cursor = db.rawQuery(sql, null);

        int count = 0;
        if(cursor.moveToFirst()){
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public List<Book> queryAllBooks(){
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(context, "library", MySQLiteOpenHelper.Version);
        SQLiteDatabase db =dbHelper.getReadableDatabase();

        String sql = "select id, bname, author, publisher, description from book";
        Cursor cursor = db.rawQuery(sql, null);

        Book book = null;
        List<Book> books = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String bname = cursor.getString(1);
            String author = cursor.getString(2);
            String publisher = cursor.getString(3);
            String description = cursor.getString(4);
            book = new Book(id, bname, author, publisher, description);
            books.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return books;
    }
}
