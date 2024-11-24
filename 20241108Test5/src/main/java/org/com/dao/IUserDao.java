package org.com.dao;

import org.com.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    User findByUsername(String username) throws SQLException;

    boolean checkExistUsername(String username);

    boolean checkExistEmail(String email);

    void insert(User user);

    boolean changePassword(String email, String newEncodedPassword);

    List<User> findAll();

    User findById(int id);
}