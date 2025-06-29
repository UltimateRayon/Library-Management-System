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

    public boolean checkIn(String bookName, String userID) {
        boolean hasBorrowed = trandao.hasUserBorrowedBook(bookName, userID);

        if (hasBorrowed) {
            boolean transactionSuccess = trandao.bookCheckIn(bookName, userID);
            boolean inventoryUpdated = bookdao.bookIncrease(bookName);

            return transactionSuccess && inventoryUpdated;
        } else {
            return false;
        }
    }
    public List<String> checkOutHistory(String userId) {
        return transactiondao.getCheckOutHistory(userId);

    }

}