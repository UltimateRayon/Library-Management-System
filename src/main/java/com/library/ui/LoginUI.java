package com.library.ui;

import com.library.utils.InputHandler;

public class LoginUI {

    private final String validPassword = "Abc@123";
    private final int validId = 12345;

    public void setValueForLogIn() {
        int id = InputHandler.getInt("Enter your Id: ");
        String password = InputHandler.getString("Enter your password: ");

        validateCredentials(id, password);
    }

    private void validateCredentials(int id, String password) {
        if (id == validId && password.equals(validPassword)) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Invalid Id or Password. Please try again.");
        }
    }
}
