package org.com.dao.impl;

import org.com.config.DBConnect_22162006;
import org.com.dao.IUserDao_22162006;
import org.com.entity.User_22162006;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao_22162006 implements IUserDao_22162006 {

    @Override
    public User_22162006 findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
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
    public boolean checkExistUsername(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
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
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
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
    public void insert(User_22162006 user22162006) {
        String query =
                "INSERT INTO users (email, full_name, phone, username, password, is_active, is_admin, images) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user22162006.getEmail());
            ps.setString(2, user22162006.getFullName());
            ps.setString(3, user22162006.getPhone());
            ps.setString(4, user22162006.getUsername());
            ps.setString(5, user22162006.getPassword());
            ps.setBoolean(6, user22162006.isActive());
            ps.setBoolean(7, user22162006.isAdmin());
            ps.setString(8, user22162006.getImages());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean changePassword(String username, String newEncodedPassword) {
        String query = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newEncodedPassword);
            ps.setString(2, username);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String query = "SELECT COUNT(*) FROM users WHERE phone = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, phone);
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

    private User_22162006 mapResultSetToUser(ResultSet rs) throws SQLException {
        return User_22162006.builder()
                            .email(rs.getString("email"))
                            .fullName(rs.getString("full_name"))
                            .phone(rs.getString("phone"))
                            .username(rs.getString("username"))
                            .password(rs.getString("password"))
                            .isActive(rs.getBoolean("is_active"))
                            .isAdmin(rs.getBoolean("is_admin"))
                            .images(rs.getString("images"))
                            .build();
    }
}