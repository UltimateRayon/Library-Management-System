package com.library.utils;

public class InputValidator {
    public boolean isValidPassword(String password) {

        boolean isValid1= hasMinimumLength(password);
        boolean isValid2= hasSpecialCharacter(password);
        boolean isValid3= hasUppercaseLetter(password);
        boolean isValid4= hasLowercaseLetter(password);
        boolean isValid5= hasDigit(password);

        return (isValid1&&isValid2&&isValid3&&isValid4&&isValid5);
    }

    private boolean hasMinimumLength(String password) {
        if (password.length() < 6) {
            System.out.println();
            System.out.println("❌ Password Must Be At Least 6 Characters Long! ❌");
            return false;
        }
        return true;
    }

    private boolean hasSpecialCharacter(String password) {
        if (!password.matches(".*[!@#$%^&()_+=\\-{}\\[\\]:;\"'<>,.?/~|\\\\].*")) {
            System.out.println();
            System.out.println("❌ Password Must Contain At Least One Special Character! ❌");
            return false;
        }
        return true;
    }

    private boolean hasUppercaseLetter(String password) {
        if (!password.matches(".*[A-Z].*")) {
            System.out.println();
            System.out.println("❌ Password Must Contain At Least One Uppercase Letter! ❌");
            return false;
        }
        return true;
    }

    private boolean hasLowercaseLetter(String password) {
        if (!password.matches(".*[a-z].*")) {
            System.out.println();
            System.out.println("❌ Password Must Contain At Least one Lowercase Letter! ❌");
            return false;
        }
        return true;
    }

    private boolean hasDigit(String password) {
        if (!password.matches(".*\\d.*")) {
            System.out.println();
            System.out.println("❌ Password Must Contain At Least One Digit. ❌");
            return false;
        }
        return true;
    }
}
