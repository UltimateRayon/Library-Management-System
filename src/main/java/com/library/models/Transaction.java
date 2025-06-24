package com.library.models;

import java.sql.Date;

public class Transaction {

    private int transactionId;
    private String userId;
    private String bookId;
    private Date issueDate;
    private Date returnDate;
    private int fine;

    public Transaction(int transactionId, String userId, String bookId, Date issueDate, Date returnDate, int fine) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    // Getters and setters here
    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

}
