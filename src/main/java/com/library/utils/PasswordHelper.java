package com.library.utils;
import java.security.MessageDigest;

public class PasswordHelper {

    public static String hashPassword(String password) {
        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA-256");
            byte[] passbyte = hasher.digest(password.getBytes());

            String result = "";
            for (byte b : passbyte) {
                result += String.format("%02x", b);

            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return "Error!";
        }
    }
    public static boolean verifyPassword(String password, String savedHash) {
        String newHash = hashPassword(password);
        return newHash.equals(savedHash);
    }
}