package org.com.dao.impl;

import org.com.config.DBConnect;
import org.com.dao.IUserDao;
import org.com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements IUserDao {

    @Override
    public User findByUsername(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkExistUsername(String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return false;
    }

    @Override
    public void insert(User user) {
        String query =
                "INSERT INTO user (email, full_name, phone, password, signup_date, last_login, is_admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFullname());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setDate(5, new java.sql.Date(user.getSignupDate().getTime()));
            ps.setDate(6, new java.sql.Date(user.getLastLogin().getTime()));
            ps.setBoolean(7, user.isAdmin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean changePassword(String email, String newEncodedPassword) {
        String query = "UPDATE user SET password = ? WHERE email = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newEncodedPassword);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return false;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return User.builder()
                   .id(rs.getLong("id"))
                   .email(rs.getString("email"))
                   .fullname(rs.getString("full_name"))
                   .phone(rs.getString("phone"))
                   .password(rs.getString("password"))
                   .signupDate(rs.getDate("signup_date"))
                   .lastLogin(rs.getDate("last_login"))
                   .isAdmin(rs.getBoolean("is_admin"))
                   .build();
    }
}