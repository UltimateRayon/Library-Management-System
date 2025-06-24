package com.library.ui;

import com.library.dao.BookDAO;
import com.library.models.Book;
import com.library.utils.InputHandler;

public class BookSearchUI {
 BookDAO daoBook = new BookDAO();
  Book book;

  public void bookSearch(){
      System.out.println();
      String bookName = InputHandler.getString("Enter book title: ");
      book = daoBook.findBook(bookName);
      if(book != null){
          System.out.println("====BOOK FOUND====");
          System.out.println();
          System.out.println("Book title: " + book.getTitle());
          System.out.println("Book author: " + book.getAuthor());
          System.out.println("Book ID: " + book.getId());
          System.out.println("Book genre: " + book.getGenre());
          System.out.println("Availability: " + (book.isAvailable() ? "Available" : "Not available"));
          System.out.println();
      }
      else
      {
          System.out.println("❌ Book not found ❌");
          System.out.println();}

  }


}
