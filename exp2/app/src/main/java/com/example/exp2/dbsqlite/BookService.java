package com.example.exp2.dbsqlite;

import android.content.Context;

import com.example.exp2.entity.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao;

    public BookService(Context context) {
        this.bookDao = new BookDao(context);
    }

    public boolean addBook(Book book) {
        if (!bookDao.isExisted(book.getId())) {//不存在
            bookDao.addBook(book);
            return true;
        } else {
            System.out.println("此书已存在！");
            return false;
        }
    }

    public boolean deleteBookById(int bookId) {
        if (bookDao.isExisted(bookId)) {
            bookDao.deleteBookById(bookId);
            return true;
        }
        return false;
    }

    public boolean updateBookById(int bookId, Book Book) {
        if (bookDao.isExisted(bookId)) {
            bookDao.updateBookById(bookId, Book);
            return true;
        }
        return false;
    }

    //根据学号查询学生
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    //查询全部学生
    public List<Book> queryAllBooks() {
        return bookDao.queryAllBooks();
    }

    public int getTotalCount() {
        return bookDao.getTotalCount();
    }
}
