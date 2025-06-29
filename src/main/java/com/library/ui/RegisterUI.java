package com.library.ui;

import com.library.services.UserService;
import com.library.utils.InputHandler;
import com.library.utils.InputValidator;

import java.lang.classfile.Label;


public class RegisterUI {
    private UserService userService = new UserService();
    public InputValidator passCheck=new InputValidator();

    public void setAllValuesForRegister() {
        System.out.println();
        System.out.println("\uD83D\uDC64 Register A New Account \uD83D\uDC64");
        System.out.println("Input 0 To Cancel");
        System.out.println();
        String name = InputHandler.getString("Enter your full name: ");
        if(name.equals("0") ){return;}
        String id = InputHandler.getString("Enter your ID: ");
        if(id.equals("0")){return;}
        String phone=InputHandler.getString("Enter your phone number: ");
        if(phone.equals("0")){return;}
        System.out.println();

        String password;
        String repassword;
        System.out.println("""
                Enter your password following these instructions:
                🗹 Should be at least 6 characters long.
                🗹 Must contain a special character.
                🗹 Must include both uppercase and lowercase letters.
                🗹 Must include at least one number.
                """);


        boolean isPasswordValid;
        do {
            password = InputHandler.getString("Choose a password: ");
            repassword = InputHandler.getString("Re-enter password: ");
            if (!password.equals(repassword)) {
                System.out.println("❌ Passwords Do Not Match. Try Again! ❌");
            }
            isPasswordValid= passCheck.isValidPassword(password);

        } while (!password.equals(repassword) || !isPasswordValid);
        String success = userService.register(id, name, phone, password);
        if (success.equals("Success")) {
            System.out.println();
            System.out.println("✅ Registration Successful! You May Now Log In. ✅");
        } else if(success.equals("Duplicate")){
            System.out.println();
            System.out.println("🗹 User Already Exists. 🗹");
        } else {
            System.out.println("❌ Registration Failed. Please Try Again! ❌");
        }
    }
}
