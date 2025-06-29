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

        do {
            System.out.println();
            System.out.println("==== MAIN MENU ====");
            System.out.println();
            System.out.println("1) User Overview");
            System.out.println("2) Search");
            System.out.println("3) Check Out");
            System.out.println("4) Check In");
            System.out.println("5) Transaction");
            System.out.println("0) Log Out");

            int input = InputHandler.getInt("Enter your choice: ");
            System.out.println();

            switch (input) {
                case 1 -> {

                    System.out.println("\uD83E\uDEAA User Account Overview \uD83E\uDEAA");
                    System.out.println("Your Name: " + user.getName());
                    System.out.println("Your ID: " + user.getId());
                    System.out.println("Your Phone Number: " + user.getPhone());
                    System.out.println();
                }

                case 2 -> bookFind.bookSearch();

                case 3 -> {
                    System.out.println("⬆\uFE0F Check Out ⬆\uFE0F");
                    System.out.println("Input 0 To Cancel");
                    System.out.println();
                    String bookName = InputHandler.getString("Enter book name to check out: ");
                    if(bookName.equals("0")) menu();
                    else{
                    transactionService.checkOut(bookName, user.getId());}
                }

                case 4 -> {
                    System.out.println("⬇\uFE0F Check In ⬇\uFE0F");
                    System.out.println("Input 0 To Cancel");
                    System.out.println();
                    String bookName = InputHandler.getString("Enter book name to check in: ");
                    if (bookName.equals("0")) menu();
                    else{
                    boolean result = transactionService.checkIn(bookName, user.getId());

                    if (result) {
                        System.out.println("✅ Book Check-In Successful. ✅");
                    } else {
                        System.out.println("❌ Check-In Failed. Either The Book Was Not Borrowed Or An Error Occurred. ❌");
                    }
                }}


                case 5 -> {
                    List<String> checkOutHistory = transactionService.checkOutHistory(user.getId());
                    if (checkOutHistory != null) {
                        for (String i : checkOutHistory) {
                            System.out.println(i);
                        }
                    }

                }

                case 0 -> {
                    exit = true;
                    System.out.println("Logging Out... ➡\uFE0F");
                }

                default -> System.out.println("❌ Invalid Choice! Please Try Again.❌");
            }


        } while(!exit);
    }
}