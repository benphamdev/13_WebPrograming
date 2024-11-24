package org.com.dao;

import org.com.entity.User_22162006;

import java.sql.SQLException;

public interface IUserDao_22162006 {
    User_22162006 findByUsername(String username) throws SQLException;

    boolean checkExistUsername(String username);

    boolean checkExistEmail(String email);

    void insert(User_22162006 user22162006);

    boolean changePassword(String email, String newEncodedPassword);

    boolean checkExistPhone(String phone);
}