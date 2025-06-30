package com.library;

import com.library.models.User;
import com.library.ui.LoginUI;
import com.library.ui.MainMenuUI;
import com.library.ui.RegisterUI;
import com.library.utils.AppContext;
import com.library.utils.InputHandler;

public class Main {
    public static void main(String[] args) {
        AppContext.isGUI=false;
        RegisterUI registerUI = new RegisterUI();
        LoginUI loginUI = new LoginUI();

        while (true) {
            System.out.println();
            System.out.println("\n==== WELCOME TO THE LIBRARY MANAGEMENT SYSTEM ====");
            System.out.println();
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
                        System.out.println();
                        System.out.println("❌ Error Login! Try Again! ❌");
                    }
                }
                case "0" -> {
                    System.out.println();
                    System.out.println("==== SEE YOU SOON ====");
                    InputHandler.closeScanner();
                    return;
                }
                default -> System.out.println("\n❌ Invalid Choice. ❌");
            }
        }
    }
}
