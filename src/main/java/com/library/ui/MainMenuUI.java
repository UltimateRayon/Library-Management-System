package com.library.ui;

import com.library.models.User;
import com.library.services.UserService;
import com.library.utils.InputHandler;

public class MainMenuUI {

    UserService userService = new UserService();


        User user;


        public MainMenuUI(User user){
            this.user = user;



            menu();
        }

        BookSearchUI bookFind = new BookSearchUI();

        public void menu(){


            boolean exit = false;

            while(!exit) {

                System.out.println("====WELCOME TO THE LIBRARY MANAGEMENT SYSTEM====");
                System.out.println();
                System.out.println("1) User Overview");
                System.out.println("2) Search");
                System.out.println("3) Check Out");
                System.out.println("4) Check In");
                System.out.println("5) Transaction");
                System.out.println("0) Log Out");

                int inputx = InputHandler.getInt("Enter your choice: ");

                switch (inputx) {
                    case 1 -> userService.userOverview(user);

                    case 2 -> bookFind.bookSearch();
//
//                    case 3 -> checkOut();
//
//                    case 4 -> Ck in;
//
//                    case 5 -> getTransaction();

                    case 0 -> exit = true;

                    default -> System.out.println("Invalid choice! Please Try Again.");
                }
            }
        }

}

