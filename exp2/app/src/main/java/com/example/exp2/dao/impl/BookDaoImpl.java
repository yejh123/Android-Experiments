package com.example.exp2.dao.impl;

import com.example.exp2.dao.IBookDao;
import com.example.exp2.entity.Book;
import com.example.exp2.util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class BookDaoImpl implements IBookDao {
    private static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PWD = "861861";

    @Override
    public boolean addBook(Book book) {
        String sql = "insert into book(bname, author, publisher, description) values(?,?,?,?)";
        Object[] params = {book.getName(), book.getAuthor(), book.getPublisher(), book.getDesc()};
        return DBUtil.executeUpdate(sql, params);
    }

    @Override
    public boolean deleteBookById(int bookId) {
        String sql = "delete from book where id=?";
        return DBUtil.executeUpdate(sql, new Object[bookId]);
    }

    @Override
    public boolean updateBookById(int bookId, Book book) {
        String sql = "update book set bname=?, author=?, publisher=?, description=? where id=?;";
        Object[] params = {book.getName(), book.getAuthor(), book.getPublisher(), book.getDesc(), book.getId()};
        return DBUtil.executeUpdate(sql, params);
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from book";
        return DBUtil.getTotalCount(sql);
    }

    @Override
    public boolean isExisted(int bookId) {
        return getTotalCount() >=1;
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select id, bname, author, publisher, description from book where id=?";
        Object[] params = {id};
        ResultSet rs = DBUtil.executeQuery(sql, params);
        Book book = null;
        try {
            if (rs.next()) {
                int bookId = rs.getInt(1);
                String bookName = rs.getString(2);
                String author = rs.getString(3);
                String publisher = rs.getString(4);
                String desc = rs.getString(5);
                book = new Book(bookId, bookName, author, publisher, desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> queryAllBooks() {
        return null;
    }
}
