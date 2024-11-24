package org.com.service.impl;

import org.com.dao.IUserDao_22162006;
import org.com.dao.impl.UserDao_22162006;
import org.com.entity.User_22162006;
import org.com.service.IUserService_22162006;

import static org.com.util.PasswordUtil_22162006.encodePassword;
import static org.com.util.PasswordUtil_22162006.matchPassword;

public class UserService_22162006Impl implements IUserService_22162006 {

    IUserDao_22162006 userDao = new UserDao_22162006();

    @Override
    public User_22162006 get(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(User_22162006 user22162006) {
        userDao.insert(user22162006);
    }

    @Override
    public User_22162006 login(String username, String password) {
        User_22162006 user22162006 = this.get(username);
        // Check if the password matches the encoded password
        if (user22162006 != null && matchPassword(password, user22162006.getPassword())) {
            return user22162006;
        }
        return null;
    }

    @Override
    public boolean register(String username, String password, String email, String fullName, int roleId, String phone) {
        // Controller has already checked if username and email are unique, so we don't need to do this again (performance boost :) )
        // Hash password by bcrypt
        User_22162006 user22162006 = User_22162006.builder()
                                                  .email(email)
                                                  .fullName(fullName)
                                                  .phone(phone)
                                                  .password(encodePassword(password))
                                                  .username(username)
                                                  .isActive(true)
                                                  .images("default.jpg")
                                                  .isAdmin(false)
                                                  .build();
        userDao.insert(user22162006);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return false;
    }

    @Override
    public boolean changePassword(String email, String newPassword) {
        return userDao.changePassword(email, encodePassword(newPassword));
    }
}