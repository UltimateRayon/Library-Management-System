package com.library.ui;

import com.library.models.Book;
import com.library.services.BookService;
import com.library.utils.InputHandler;

import java.util.List;

public class BookSearchUI {
  BookService bookService=new BookService();
  Book book;

  public void bookSearch(){
      System.out.println();
      System.out.println("\uD83D\uDD0D Search Books \uD83D\uDD0D");
      System.out.println("Input 0 to Cancel.");
      System.out.println("Input * to see all books");
      System.out.println();
      String bookName = InputHandler.getString("Enter book title: ");
      if(bookName.equals("0")){return;}
      if(bookName.equals("*")) {
          List<String> bookList= bookService.bookSearch();
          System.out.println("-------------------------");
          for(String entry:bookList){
              System.out.println(entry);
          }
          System.out.println("-------------------------");
          return;
      }

      book = bookService.bookSearch(bookName);
      if(book != null){
          System.out.println();
          System.out.println("-------------------------");
          System.out.println("\uD83D\uDCD8 Book Found \uD83D\uDCD8");
          System.out.println();
          System.out.println("Book Title: " + book.getTitle());
          System.out.println("Book Author: " + book.getAuthor());
          System.out.println("Book ID: " + book.getId());
          System.out.println("Book Genre: " + book.getGenre());
          System.out.println("Availability: " + (book.isAvailable() ? "Available" : "Not available"));
          System.out.println("-------------------------");
          System.out.println();
      }
      else
      {
          System.out.println();
          System.out.println("❌ Book Not Found. ❌");
          }

  }

}
