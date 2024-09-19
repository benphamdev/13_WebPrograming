package org.example.com.dao;

import org.example.com.models.User;

import java.sql.SQLException;

public interface IUserDao {
    User findByUsername(String username) throws SQLException;

    boolean checkExistUsername(String username);

    boolean checkExistEmail(String email);

    void insert(User user);

    boolean changePassword(String email, String newEncodedPassword);

    boolean checkExistPhone(String phone);
}