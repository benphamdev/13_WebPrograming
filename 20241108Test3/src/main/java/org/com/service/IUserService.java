package org.com.service;

import org.com.entity.User;

public interface IUserService {
    User get(String username);

    void insert(User user);

    User login(String username, String password);

    boolean register(String username, String password, String email, String fullName, int roleId, String phone);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

    boolean changePassword(String email, String newPassword);
}