package com.library.services;

import com.library.dao.UserDAO;
import com.library.models.User;
import com.library.utils.AppContext;
import com.library.utils.PasswordHelper;

public class UserService {

    private final UserDAO userDAO = new UserDAO();
    private User user;

    //Returns a User if credentials match, else null
    public User login(String id, String password) {
        User user = userDAO.getUserById(id);
        if (user != null && PasswordHelper.verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    //Registers a new user; returns true on success
    public String register(String id, String name, String phone, String password) {
        AppContext.isGUI=true;
        if (userDAO.getUserById(id) != null) {
            return "Duplicate";
        }
        String hashed = PasswordHelper.hashPassword(password);
        User newUser = new User(id, name, phone, hashed);
        return (userDAO.saveUser(newUser))? "Success" : "Error";
    }
}

