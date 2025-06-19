package com.library.ui;

import com.library.services.UserService;
import com.library.utils.InputHandler;
import com.library.utils.InputValidator;


public class RegisterUI {
    private final UserService userService = new UserService();
    public InputValidator passCheck=new InputValidator();

    public void setAllValuesForRegister() {
        System.out.println("Register a new account");

        String name = InputHandler.getString("Enter your full name: ");
        String id = InputHandler.getString("Enter your ID: ");
        String phone=InputHandler.getString("Enter you phone number: ");

        String password;
        String repassword;
        System.out.println("""
                Enter your password following these instructions:
                1. Should be at least 5 characters long.
                2. Must contain a special character.
                3. Must include both uppercase and lowercase letters.
                4. Must include at least one number.
                """);
        boolean isPasswordValid;
        do {
            password = InputHandler.getString("Choose a password: ");
            repassword = InputHandler.getString("Re-enter password: ");
            if (!password.equals(repassword)) {
                System.out.println("Passwords do not match. Try again.");
            }
            isPasswordValid= passCheck.isValidPassword(password);
        } while (!password.equals(repassword) && !isPasswordValid);

        boolean success = userService.register(id, name, phone, password);
        if (success) {
            System.out.println("Registration successful! You may now log in.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }
}
