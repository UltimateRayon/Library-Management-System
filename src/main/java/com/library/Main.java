package com.library;
import com.library.ui.LoginUI;
import com.library.ui.Register;
import com.library.utils.InputHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== Welcome to the System ======");
        System.out.println("1. Register");
        System.out.println("2. Log In");
        System.out.println("0. Exit");
        System.out.println("===================================");

        while (true) {
            String choice = InputHandler.getString("Enter your choice: ");

            switch (choice) {
                case "1" -> {
                    Register register = new Register();
                    register.setAllValuesForRegister();
                }
                case "2" -> {
                    LoginUI login = new LoginUI();
                    login.setValueForLogIn();
                }
                case "0" -> {
                    System.out.println("Exiting the program. Goodbye!");
                    InputHandler.closeScanner();
                    return;
                }
                default -> System.out.println("Invalid Choice. Please try again.");
            }

            System.out.println();
        }
    }
}