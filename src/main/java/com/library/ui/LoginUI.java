package com.library.ui;

import com.library.models.User;
import com.library.services.UserService;
import com.library.utils.InputHandler;

public class LoginUI {
    private final UserService userService = new UserService();

    /** Shows prompts and returns the logged‑in User (or null) */
    public User setValueForLogIn() {
        System.out.println("🔐 Log In");

        String id = InputHandler.getString("Enter your ID: ");
        String password = InputHandler.getString("Enter your password: ");

        User user = userService.login(id, password);
        if (user != null) {
            System.out.println("✅ Welcome back, " + user.getName() + "!");
        } else {
            System.out.println("❌ Invalid ID or password.");
            setValueForLogIn();
        }
            return user;
    }
}
