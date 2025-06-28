package com.library.utils;

public class InputValidator {
    public boolean isValidPassword(String password) {
        boolean isValid = true;

        isValid= hasMinimumLength(password);
        isValid= hasSpecialCharacter(password);
        isValid= hasUppercaseLetter(password);
        isValid= hasLowercaseLetter(password);
        isValid= hasDigit(password);

        return isValid;
    }

    private boolean hasMinimumLength(String password) {
        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long.");
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
