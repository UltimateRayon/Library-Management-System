package com.library.services;

import com.library.dao.UserDAO;
import com.library.models.User;
import com.library.utils.PasswordHelper;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    /** Returns a User if credentials match, else null */
    public User login(String id, String password) {
        User user = userDAO.getUserById(id);
        if (user != null && PasswordHelper.verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    /** Registers a new user; returns true on success */
    public boolean register(String id, String name, String phone, String password) {
        if (userDAO.getUserById(id) != null) {
            System.out.println("❌ ID already exists.");
            return false;
        }
        String hashed = PasswordHelper.hashPassword(password);
        User newUser = new User(id, name, phone, hashed);
        return userDAO.saveUser(newUser);
    }
}

