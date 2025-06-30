package com.library.services;

import com.library.dao.UserDAO;
import com.library.models.User;
import com.library.utils.AppContext;
import com.library.utils.PasswordHelper;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

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
        Timestamp currentTime=Timestamp.from(Instant.now());
        User newUser = new User(id, name, phone, hashed, 0, 0, currentTime);
        return (userDAO.saveUser(newUser))? "Success" : "Error";
    }

    public boolean updateFine(User user){
        int fine=user.getFine();
        if(fine>0){
            Timestamp lastTime=user.getTime();
            Timestamp currentTime=Timestamp.from(Instant.now());
            long timeDifference= ChronoUnit.DAYS.between(lastTime.toLocalDateTime(), currentTime.toLocalDateTime());
            //System.out.println(timeDifference);
            if(timeDifference>0){
                if(userDAO.updateFine(user.getId(), timeDifference))
                    //user.setFine((int)timeDifference);
                    userDAO.fetchUserInfo(user);
                return true;
            }
        }
        return true;
    }
}

