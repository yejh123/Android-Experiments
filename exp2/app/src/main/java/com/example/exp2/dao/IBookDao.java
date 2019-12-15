package com.example.exp2.dao;

import com.example.exp2.entity.Book;

import java.util.List;

public interface IBookDao {
    boolean isExisted(int bookId);

    boolean addBook(Book book);

    boolean deleteBookById(int bookId);

    boolean updateBookById(int bookId, Book book);

    int getTotalCount();

    Book queryBookById(int bookId);

    List<Book> queryAllBooks();
}
