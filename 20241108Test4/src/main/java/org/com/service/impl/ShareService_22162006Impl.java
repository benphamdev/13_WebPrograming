package org.com.service.impl;

import org.com.config.DBConnect_22162006;
import org.com.entity.Share_22162006;
import org.com.service.IShareService_22162006;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShareService_22162006Impl implements IShareService_22162006 {

    @Override
    public void save(Share_22162006 share22162006) {
        String query = "INSERT INTO shares (emails, shared_date, username, video_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, share22162006.getEmails());
            ps.setDate(2, (Date) share22162006.getSharedDate());
            ps.setString(3, share22162006.getUsername());
            ps.setLong(4, share22162006.getVideoId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Share_22162006> findAllWithUser() {
        List<Share_22162006> share22162006s = new ArrayList<>();
        String query = "SELECT s.*, u.* FROM shares s JOIN users u ON s.username = u.username";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Share_22162006 share22162006 = mapResultSetToShare(rs);
                share22162006s.add(share22162006);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return share22162006s;
    }

    @Override
    public List<Share_22162006> findAllWithVideoId(long videoId) {
        List<Share_22162006> share22162006s = new ArrayList<>();
        String query = "SELECT * FROM shares WHERE video_id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, videoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Share_22162006 share22162006 = mapResultSetToShare(rs);
                    share22162006s.add(share22162006);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return share22162006s;
    }

    private Share_22162006 mapResultSetToShare(ResultSet rs) throws SQLException {
        return Share_22162006.builder()
                             .id(rs.getLong("id"))
                             .emails(rs.getString("emails"))
                             .sharedDate(rs.getDate("shared_date"))
                             .username(rs.getString("username"))
                             .videoId(rs.getLong("video_id"))
                             .build();
    }
}