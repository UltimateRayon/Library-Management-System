package com.library.ui;

import com.library.models.User;
import com.library.services.UserService;
import com.library.utils.InputHandler;

public class LoginUI {
    private final UserService userService = new UserService();

    /**
     * Shows prompts and returns the logged‑in User (or null)
     */
    public User setValueForLogIn() {

        System.out.println("🔐 Log In 🔐");
        System.out.println("Input 0 To Cancel");
        System.out.println();

        String id = InputHandler.getString("Enter your ID: ");
        if (id.equals("0")) {
            return null;
        }
        String password = InputHandler.getString("Enter your password: ");
        if (password.equals("0")) {
            return null;
        }

        User user = userService.login(id, password);

        if (user != null) {
            System.out.println();
            System.out.println("! Welcome Back, " + user.getName() + " !");
        } else {
            System.out.println("❌ Invalid ID or password. ❌");
            System.out.println();
            setValueForLogIn();

            if (user != null && userService.updateFine(user)) {
                System.out.println("✅ Welcome back, " + user.getName() + "!");
            } else {
                System.out.println("❌ Invalid ID or password.");

            }
            return user;
        }
    return user;}

}