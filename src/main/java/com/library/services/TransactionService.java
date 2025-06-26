package com.library.services;

import com.library.dao.BookDAO;
import com.library.dao.TransactionDAO;

public class TransactionService {
    BookDAO bookdao=new BookDAO();
    TransactionDAO trandao=new TransactionDAO();

    public void checkOut(String bookName, String userID){
         boolean isBookAvailable= bookdao.bookAvailability(bookName);
         if(isBookAvailable){
            if(trandao.bookCheckOut(bookName, userID) && bookdao.bookReduce(bookName)){

                System.out.println("Book Checkout Successful");
            }
         }
         else {
             System.out.println("Book not available for checkout!");
         }
    }
}
