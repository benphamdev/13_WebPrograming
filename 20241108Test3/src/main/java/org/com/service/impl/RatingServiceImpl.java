package org.com.service.impl;

import org.com.config.DBConnect;
import org.com.entity.Rating;
import org.com.service.IRatingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingServiceImpl implements IRatingService {

    @Override
    public void save(Rating rating) {
        String query = "INSERT INTO rating (user_id, book_id, rating, review_text) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, rating.getUserId());
            ps.setLong(2, rating.getBookId());
            ps.setInt(3, rating.getRating());
            ps.setString(4, rating.getReviewText());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Rating> findAllWithUser() {
        List<Rating> ratings = new ArrayList<>();
        String query = "SELECT r.*, u.* FROM rating r JOIN user u ON r.user_id = u.id";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Rating rating = mapResultSetToRating(rs);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }

    @Override
    public List<Rating> findAllWithBookId(long bookId) {
        List<Rating> ratings = new ArrayList<>();
        String query = "SELECT * FROM rating WHERE book_id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, bookId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Rating rating = mapResultSetToRating(rs);
                    ratings.add(rating);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }


    private Rating mapResultSetToRating(ResultSet rs) throws SQLException {
        return Rating.builder()
                     .userId(rs.getLong("user_id"))
                     .bookId(rs.getLong("book_id"))
                     .rating(rs.getInt("rating"))
                     .reviewText(rs.getString("review_text"))
                     .build();
    }
}