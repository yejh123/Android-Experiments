package com.example.exp2.service.impl;

import com.example.exp2.dao.IBookDao;
import com.example.exp2.dao.impl.BookDaoImpl;
import com.example.exp2.entity.Book;
import com.example.exp2.service.IBookService;
import com.example.exp2.util.DBUtil;

import java.util.List;

public class BookServiceImpl implements IBookService {
    IBookDao bookDao = new BookDaoImpl();

    @Override
    public boolean addBook(Book book) {
        if (!bookDao.isExisted(book.getId())) {//不存在
            return bookDao.addBook(book);
        } else {
            System.out.println("此书已存在！");
            return false;
        }
    }

    @Override
    public boolean deleteBookById(int bookId) {
        if (bookDao.isExisted(bookId)) {
            return bookDao.deleteBookById(bookId);
        }
        return false;
    }

    @Override
    public boolean updateBookById(int bookId, Book Book) {
        if (bookDao.isExisted(bookId)) {
            return bookDao.updateBookById(bookId, Book);
        }
        return false;
    }
    
    //根据学号查询学生
    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    //查询全部学生
    @Override
    public List<Book> queryAllBooks() {
        return bookDao.queryAllBooks();
    }

    @Override
    public int getTotalCount() {
        return bookDao.getTotalCount();
    }
}
