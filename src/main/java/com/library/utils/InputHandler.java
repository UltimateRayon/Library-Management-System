package com.library.utils;

import java.util.Scanner;

    public class InputHandler {

        private static final Scanner scanner = new Scanner(System.in);

        // Get a non-empty string from user
        public static String getString(String prompt) {
            String input;
            do {
                System.out.print(prompt);
                input = scanner.nextLine().trim();
                if(input.equals("0")) return "0";
                if (input.isEmpty()) {
                    System.out.println("❌ Input Cannot Be Empty. Please Try Again! ❌");
                }
            } while (input.isEmpty());
            return input;
        }

        // Get a valid integer from user
        public static int getInt(String prompt) {
            while (true) {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Please enter a valid integer! ❌");
                }
            }
        }

        // Get a valid double from user
        public static double getDouble(String prompt) {
            while (true) {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                try {
                    return Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Please Enter A Valid Number! ❌");
                }
            }
        }

        // close the scanner if needed
        public static void closeScanner() {
            scanner.close();
        }
    }

