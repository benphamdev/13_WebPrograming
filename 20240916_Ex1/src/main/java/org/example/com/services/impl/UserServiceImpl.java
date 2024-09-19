package org.example.com.services.impl;

import org.example.com.dao.IUserDao;
import org.example.com.dao.impl.UserDaoImpl;
import org.example.com.models.User;
import org.example.com.services.IUserService;

import static org.example.com.utils.PasswordUtil.encodePassword;
import static org.example.com.utils.PasswordUtil.matchPassword;

public class UserServiceImpl implements IUserService {

    IUserDao userDao = new UserDaoImpl();

    @Override
    public User get(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        // Check if the password matches the encoded password
        if (user != null && matchPassword(password, user.getPassword()))
            return user;
        return null;
    }

    @Override
    public boolean register(String username, String password, String email, String fullName, int roleId, String phone) {
        // Controller has already checked if username and email are unique, so we don't need to do this again (performance boost :) )
        // Hash password by bcrypt
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        User user = User.builder()
                        .username(username)
                        .password(encodePassword(password))
                        .email(email)
                        .fullName(fullName)
                        .roleId(roleId)
                        .phone(phone)
                        .createdDate(date)
                        .build();
        userDao.insert(user);
        return true;
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public boolean changePassword(String email, String newPassword) {
        return userDao.changePassword(email, encodePassword(newPassword));
    }

}

