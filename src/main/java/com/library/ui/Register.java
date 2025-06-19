package com.library.ui;

import com.library.utils.InputHandler;

public class Register {
    private String name, password;
    private long id;

    public void setAllValuesForRegister() {
        System.out.println("To create an account, provide the following information:");

        this.name = InputHandler.getString("Enter your name: ");
        this.id = InputHandler.getInt("Enter your Id: ");

        System.out.println("""
                Enter your password following these instructions:
                1. Should be at least 5 characters long.
                2. Must contain a special character.
                3. Must include both uppercase and lowercase letters.
                4. Must include at least one number.
                """);

        while (true) {
            String password = InputHandler.getString("Enter password: ");

            if (!isValidPassword(password)) {
                System.out.println("Password does not meet the criteria. Try again.");
                continue;
            }

            String repassword = InputHandler.getString("Rewrite your password: ");

            if (!password.equals(repassword)) {
                System.out.println("Passwords do not match. Try again.");
            } else {
                this.password = password;
                break;
            }
        }

        System.out.println("Registration Successful!");
        System.out.println("Name: " + this.name + ", ID: " + this.id);
//         LoginUI obj = new LoginUI();
//         obj.setValueForLogIn();
    }

    private boolean isValidPassword(String password) {
        boolean isValid = true;

        if (!hasMinimumLength(password)) isValid = false;
        if (!hasSpecialCharacter(password)) isValid = false;
        if (!hasUppercaseLetter(password)) isValid = false;
        if (!hasLowercaseLetter(password)) isValid = false;
        if (!hasDigit(password)) isValid = false;

        return isValid;
    }

    private boolean hasMinimumLength(String password) {
        if (password.length() < 5) {
            System.out.println("Password must be at least 5 characters long.");
            return false;
        }
        return true;
    }

    private boolean hasSpecialCharacter(String password) {
        if (!password.matches(".*[!@#$%^&()_+=\\-{}\\[\\]:;\"'<>,.?/~|\\\\].*")) {
            System.out.println("Password must contain at least one special character.");
            return false;
        }
        return true;
    }

    private boolean hasUppercaseLetter(String password) {
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }
        return true;
    }

    private boolean hasLowercaseLetter(String password) {
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }
        return true;
    }

    private boolean hasDigit(String password) {
        if (!password.matches(".*\\d.*")) {
            System.out.println("Password must contain at least one digit.");
            return false;
        }
        return true;
    }
}
