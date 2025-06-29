package com.library.ui;

import java.util.List;
import com.library.models.User;
import com.library.services.TransactionService;
import com.library.services.UserService;
import com.library.utils.InputHandler;

public class MainMenuUI {
    UserService userService = new UserService();
    BookSearchUI bookFind = new BookSearchUI();
    TransactionService transactionService = new TransactionService();
    User user;

    public MainMenuUI(User user) {
        this.user = user;
        menu();
    }

    public void menu() {
        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("==== MAIN MENU ====");
            System.out.println();
            System.out.println("1) User Overview");
            System.out.println("2) Search");
            System.out.println("3) Check Out");
            System.out.println("4) Check In");
            System.out.println("5) Transaction");
            System.out.println("0) Log Out");

            int inputx = InputHandler.getInt("Enter your choice: ");

            switch (inputx) {
                case 1 -> {
                    System.out.println();
                    System.out.println("\uD83D\uDDF9 User Account Overview \uD83D\uDDF9");
                    System.out.println("Your Name: " + user.getName());
                    System.out.println("Your ID: " + user.getId());
                    System.out.println("Your Phone Number: " + user.getPhone());
                    System.out.println("Your Borrowed Books: " + user.getBorrowBook());
                    System.out.println("Your Fine: " + user.getFine()+ " days");
                    System.out.println();
                }

                case 2 -> bookFind.bookSearch();

                case 3 -> {
                    System.out.println();
                    System.out.println("⬆\uFE0F Book Check Out ⬆\uFE0F");
                    System.out.println();
                    String bookName = InputHandler.getString("Enter book name to check out: ");
                    if(bookName.equals("0")){menu();}

                    String checkoutResult=transactionService.checkOut(bookName, user);
                    if(checkoutResult.equals("Success")){
                        System.out.println();
                        System.out.println("✅ Book Checkout Successful ✅");
                    } else if(checkoutResult.equals("NA")){
                        System.out.println();
                        System.out.println("❌ Book Not Found Or Not Available For Checkout! ❌");
                    } else if(checkoutResult.equals("Overdue")){
                        System.out.println();
                        System.out.println("❌ You have already Checked out 5 books or you have fine overdue.");
                    } else if(checkoutResult.equals("Not Unique")){
                        System.out.println();
                        System.out.println("❌ You already have a copy of this book.");
                    }else {
                        System.out.println();
                        System.out.println("❌ Check out error. Please try again! ❌");
                    }
                }

                case 4 -> {
                    System.out.println();
                    System.out.println("⬇\uFE0F Book Check In ⬇\uFE0F");
                    System.out.println();
                    String bookName = InputHandler.getString("Enter book name to check in: ");

                    if(bookName.equals("0")){menu();}
                    String result = transactionService.checkIn(bookName, user);

                    if (result.equals("Successful")) {
                        System.out.println("✅ Book Check-In Successful");
                    } else if (result.equals("no book")) {
                        System.out.println("❌ Check-In Failed: The book was not borrowed.");
                    } else {
                        System.out.println("⚠️ Check-In Error!");
                    }
                }


                 case 5 -> {
                    List<String> checkOutHistory = transactionService.checkOutHistory(user);
                    if (checkOutHistory!=null){
                        for (String i:checkOutHistory) {
                                     System.out.println(i);
                        }
                    }
                }

                case 0 -> {
                    exit = true;
                    System.out.println();
                    System.out.println("Logging out... ➡\uFE0F");
                }

                default -> {
                    System.out.println();
                    System.out.println("❌ Invalid Choice! Please Try Again! ❌");
                }

            }


        }
    }
}