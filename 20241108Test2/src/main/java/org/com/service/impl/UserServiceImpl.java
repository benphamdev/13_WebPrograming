package org.com.service.impl;

import org.com.dao.IUserDao;
import org.com.dao.impl.UserDao;
import org.com.entity.User;
import org.com.service.IUserService;

import static org.com.util.PasswordUtil.encodePassword;
import static org.com.util.PasswordUtil.matchPassword;

public class UserServiceImpl implements IUserService {

    IUserDao userDao = new UserDao();

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
        User user = User.builder()
                        .username(username)
                        .password(encodePassword(password))
                        .fullName(fullName)
//                        .roleId(roleId)
                        .build();
        userDao.insert(user);
        return true;
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

//    @Override
//    public boolean checkExistEmail(String email) {
//        return userDao.checkExistEmail(email);
//    }

//    @Override
//    public boolean checkExistPhone(String phone) {
//        return userDao.checkExistPhone(phone);
//    }

    @Override
    public boolean changePassword(String email, String newPassword) {
        return userDao.changePassword(email, encodePassword(newPassword));
    }

}

