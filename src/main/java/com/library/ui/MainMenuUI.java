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
            System.out.println("==== WELCOME TO THE LIBRARY MANAGEMENT SYSTEM ====");
            System.out.println();
            System.out.println("1) User Overview");
            System.out.println("2) Search");
            System.out.println("3) Check Out");
            System.out.println("4) Check In");
            System.out.println("5) Transaction");
            System.out.println("0) Log Out");

            int input = InputHandler.getInt("Enter your choice: ");

            switch (input) {
                case 1 -> userService.userOverview(user);

                case 2 -> bookFind.bookSearch();

                case 3 -> {
                    String bookName = InputHandler.getString("Enter book name to check out: ");
                    transactionService.checkOut(bookName, user.getId());
                }

                case 4 -> {
                    String bookName = InputHandler.getString("Enter book name to check in: ");
                    boolean result = transactionService.checkIn(bookName, user.getId());

                    if (result) {
                        System.out.println("Book Check-In Successful");
                    } else {
                        System.out.println("Check-In Failed. Either the book was not borrowed or an error occurred.");
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
                    System.out.println("Logging out... Goodbye!");
                }

                default -> System.out.println("Invalid choice! Please try again.");
            }


        }
    }
}