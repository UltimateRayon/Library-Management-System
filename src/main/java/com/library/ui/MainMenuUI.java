package com.library.ui;

import java.util.List;
import com.library.models.User;
import com.library.services.TransactionService;
import com.library.utils.InputHandler;

public class MainMenuUI {
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
            System.out.println("2) Search Book");
            System.out.println("3) Check Out");
            System.out.println("4) Check In");
            System.out.println("5) Checkout History");
            System.out.println("0) Log Out");

            int inputx = InputHandler.getInt("Enter your choice: ");

            switch (inputx) {
                case 1 -> {
                    System.out.println();
                    System.out.println("-------------------------");
                    System.out.println("\uD83D\uDDF9 User Account Overview \uD83D\uDDF9");
                    System.out.println("Your Name: " + user.getName());
                    System.out.println("Your ID: " + user.getId());
                    System.out.println("Your Phone Number: " + user.getPhone());
                    System.out.println("Your Borrowed Books: " + user.getBorrowBook());
                    System.out.println("Your Fine: " + user.getFine()+ " days");
                    System.out.println("-------------------------");
                    System.out.println();
                }

                case 2 -> bookFind.bookSearch();

                case 3 -> {
                    System.out.println();
                    System.out.println("⬆️ Book Check Out ⬆️");
                    System.out.println("Input 0 to Cancel.");
                    System.out.println();
                    String bookName = InputHandler.getString("Enter book name to check out: ");
                    if(bookName.equals("0")){menu();}

                    String checkoutResult=transactionService.checkOut(bookName, user);
                    switch (checkoutResult) {
                        case "Success" -> {
                            System.out.println();
                            System.out.println("✅ Book Checkout Successful ✅");
                        }
                        case "NA" -> {
                            System.out.println();
                            System.out.println("❌ Book Not Found Or Not Available For Checkout! ❌");
                        }
                        case "Overdue" -> {
                            System.out.println();
                            System.out.println("❌ You Have Already Checked Out 5 Books Or You Have Fine Overdue. ❌");
                        }
                        case "Not Unique" -> {
                            System.out.println();
                            System.out.println("❌ You Already Have A Copy Of This Book. ❌");
                        }
                        default -> {
                            System.out.println();
                            System.out.println("❌ Check Out Error. Please Try Again! ❌");
                        }
                    }
                }

                case 4 -> {
                    System.out.println();
                    System.out.println("⬇️ Book Check In ⬇️");
                    System.out.println("Input 0 To Cancel.");
                    System.out.println();
                    String bookName = InputHandler.getString("Enter book name to check in: ");

                    if(bookName.equals("0")){menu();}
                    String result = transactionService.checkIn(bookName, user);

                    if (result.equals("Successful")) {
                        System.out.println("✅ Book Check-In Successful ✅");
                    } else if (result.equals("no book")) {
                        System.out.println("❌ Check-In Failed: The book was not borrowed. ❌");
                    } else {
                        System.out.println("⚠️ Check-In Error! ⚠️");
                    }
                }


                 case 5 -> {
                     System.out.println();
                     System.out.println("\uD83D\uDCDC Checkout History \uD83D\uDCDC");
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
                    System.out.println("Logging out... ➡️");
                }

                default -> {
                    System.out.println();
                    System.out.println("❌ Invalid Choice! Please Try Again! ❌");
                }

            }


        }
    }
}