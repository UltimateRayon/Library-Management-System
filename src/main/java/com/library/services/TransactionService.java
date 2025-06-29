package com.library.services;

import com.library.dao.BookDAO;
import com.library.dao.TransactionDAO;
import com.library.dao.UserDAO;
import com.library.models.User;

import java.util.List;

public class TransactionService {
    User user;
    UserDAO userdao=new UserDAO();
    BookDAO bookdao = new BookDAO();
    TransactionDAO trandao = new TransactionDAO();
    TransactionDAO transactiondao=new TransactionDAO();

    public String checkOut(String bookName, User user) {
        boolean isBookAvailable = bookdao.bookAvailability(bookName);
        if (isBookAvailable) {
            if(user.getBorrowBook()<5 && user.getFine()==0) {
                if (trandao.bookCheckOut(bookName, user.getId()) && bookdao.bookReduce(bookName) && userdao.borrowBookUpdate(user.getId(), 1)) {
                    user.setBorrowBook(user.getBorrowBook()+1);
                    return "Success";
                }
                else {
                    return "Error";
                }
            } else {
                return "Overdue";
            }
        }
        return "NA";
    }

    public String checkIn(String bookName, String userID) {
        TransactionDAO trandao = new TransactionDAO();
        BookDAO bookdao = new BookDAO();
        UserDAO userdao = new UserDAO();

        boolean hasBorrowed = trandao.hasUserBorrowedBook(bookName, userID);
        if (!hasBorrowed) {
            return "no book";
        }

        if (trandao.bookCheckIn(bookName, userID) && bookdao.bookIncrease(bookName) && userdao.borrowBookUpdate(userID, -1)) {
            return "Successful";
        } else {
            return "error";
        }
    }

    public List<String> checkOutHistory(String userId) {
        return transactiondao.getCheckOutHistory(userId);

    }


}