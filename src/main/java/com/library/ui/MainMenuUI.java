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
                        System.out.println("❌ You Have Already Checked Out 5 Books Or You Have Fine overdue. ❌");
                    } else {
                        System.out.println();
                        System.out.println("❌ Check Out Error. Please Try Again! ❌");
                    }
                }

                case 4 -> {
                    System.out.println();
                    System.out.println("⬇\uFE0F Book Check In ⬇\uFE0F");
                    System.out.println();
                    String bookName = InputHandler.getString("Enter book name to check in: ");
                    if(bookName.equals("0")){menu();}
                    boolean result = transactionService.checkIn(bookName, user.getId());

                    if (result) {
                        System.out.println();
                        System.out.println("✅ Book Check-In Successful ✅");
                    } else {
                        System.out.println();
                        System.out.println("❌ Check-In Failed. Either The Book Was Not Borrowed Or An Error Occurred. ❌");
                    }
                }

                 case 5 ->
                         {
                             List<String> checkOutHistory = transactionService.checkOutHistory(user.getId());
                             if (checkOutHistory!=null){
                                 for (String i:checkOutHistory)
                                 {
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