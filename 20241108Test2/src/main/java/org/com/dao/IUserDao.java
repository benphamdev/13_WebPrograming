package org.com.dao;

import org.com.entity.User;

import java.sql.SQLException;

public interface IUserDao {
    User findByUsername(String username) throws SQLException;

    boolean checkExistUsername(String username);

//    boolean checkExistEmail(String email);

    void insert(User user);

    boolean changePassword(String email, String newEncodedPassword);

//    boolean checkExistPhone(String phone);
}