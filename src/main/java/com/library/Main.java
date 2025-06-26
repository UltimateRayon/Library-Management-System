package com.library;

import com.library.models.User;
import com.library.ui.LoginUI;
import com.library.ui.MainMenuUI;
import com.library.ui.RegisterUI;
import com.library.utils.InputHandler;

public class Main {
    public static void main(String[] args) {
        RegisterUI registerUI = new RegisterUI();
        LoginUI loginUI = new LoginUI();

        while (true) {
            System.out.println("\n====== Welcome to Library Management System ======");
            System.out.println("1. Register");
            System.out.println("2. Log In");
            System.out.println("0. Exit");

            String choice = InputHandler.getString("Enter your choice: ");
            switch (choice) {
                case "1" -> registerUI.setAllValuesForRegister();
                case "2" -> {
                    User user = loginUI.setValueForLogIn();
                    if (user != null) {
                        MainMenuUI menu = new MainMenuUI(user);
                    }
                    else
                    {
                        System.out.println("Error Login! Try again");
                    }
                }
                case "0" -> {
                    System.out.println("Goodbye!");
                    InputHandler.closeScanner();
                    return;
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }
}
