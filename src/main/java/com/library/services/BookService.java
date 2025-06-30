package com.library.services;

import com.library.dao.BookDAO;
import com.library.models.Book;

import java.util.List;

public class BookService {
    BookDAO daoBook = new BookDAO();
    public Book bookSearch(String bookName) {
        return daoBook.findBook(bookName);
    }

    public List<String> bookSearch(){
        return daoBook.getBookList();
    }
}
