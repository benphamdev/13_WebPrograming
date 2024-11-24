package org.com.service;

import org.com.entity.User_22162006;

public interface IUserService_22162006 {
    User_22162006 get(String username);

    void insert(User_22162006 user22162006);

    User_22162006 login(String username, String password);

    boolean register(String username, String password, String email, String fullName, int roleId, String phone);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

    boolean changePassword(String email, String newPassword);
}