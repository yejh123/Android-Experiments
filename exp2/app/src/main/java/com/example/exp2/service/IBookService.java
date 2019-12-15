package com.example.exp2.service;

import com.example.exp2.entity.Book;

import java.util.List;

public interface IBookService {
    boolean addBook(Book book);

    boolean deleteBookById(int bookId);

    boolean updateBookById(int bookId, Book book);

    int getTotalCount();

    Book queryBookById(int id);

    List<Book> queryAllBooks();
}
