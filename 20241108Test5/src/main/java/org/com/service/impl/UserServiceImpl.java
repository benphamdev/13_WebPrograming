package org.com.service.impl;

import org.com.dao.IUserDao;
import org.com.dao.impl.UserDao;
import org.com.entity.User;
import org.com.service.IUserService;

import java.util.List;

import static org.com.util.PasswordUtil.encodePassword;
import static org.com.util.PasswordUtil.matchPassword;

public class UserServiceImpl implements IUserService {

    private final IUserDao userDao = new UserDao();

    @Override
    public User get(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
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
        if (user != null && matchPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String username, String password, String email, String fullName, int roleId, String phone) {
        // Hash the password using bcrypt
        User user = User.builder()
                        .username(username)
                        .password(encodePassword(password))
                        .avatar("default.jpg")
                        .isAdmin(false) // Default to non-admin
                        .isSeller(roleId == 2) // Role ID determines if the user is a seller
                        .build();
        userDao.insert(user);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean changePassword(String email, String newPassword) {
        return userDao.changePassword(email, encodePassword(newPassword));
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }
}
