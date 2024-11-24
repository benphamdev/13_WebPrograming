package org.com.dao.impl;

import org.com.config.DBConnect_22162006;
import org.com.dao.IVideoDao_22162006;
import org.com.entity.Video_22162006;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDao_22162006 implements IVideoDao_22162006 {

    protected Video_22162006 mapResultSetToEntity(ResultSet rs) throws SQLException {
        return Video_22162006.builder()
                             .id(rs.getLong("id"))
                             .title(rs.getString("title"))
                             .poster(rs.getString("poster"))
                             .views(rs.getInt("views"))
                             .description(rs.getString("description"))
                             .isActive(rs.getBoolean("is_active"))
                             .categoryId(rs.getInt("category_id"))
                             .build();
    }

    protected void setPreparedStatementForSave(PreparedStatement ps, Video_22162006 video22162006) throws SQLException {
        ps.setString(1, video22162006.getTitle());
        ps.setString(2, video22162006.getPoster());
        ps.setInt(3, video22162006.getViews());
        ps.setString(4, video22162006.getDescription());
        ps.setBoolean(5, video22162006.isActive());
        ps.setInt(6, video22162006.getCategoryId());
    }

    @Override
    public List<Video_22162006> findByTitle(String title) {
        List<Video_22162006> video22162006s = new ArrayList<>();
        String query = "SELECT * FROM videos WHERE title LIKE ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + title + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    video22162006s.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video22162006s;
    }

    @Override
    public List<Video_22162006> findAll(int page, int pageSize) {
        List<Video_22162006> video22162006s = new ArrayList<>();
        String query = "SELECT * FROM videos LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    video22162006s.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video22162006s;
    }

    @Override
    public List<Video_22162006> searchPaginated(String title, int page, int pageSize) {
        List<Video_22162006> video22162006s = new ArrayList<>();
        String query = "SELECT * FROM videos WHERE title LIKE ? LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, pageSize);
            ps.setInt(3, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    video22162006s.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video22162006s;
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM videos";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void insert(Video_22162006 video22162006) {
        String query =
                "INSERT INTO videos (title, poster, views, description, is_active, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setPreparedStatementForSave(ps, video22162006);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    video22162006.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Video_22162006 video22162006) {
        String query =
                "UPDATE videos SET title = ?, poster = ?, views = ?, description = ?, is_active = ?, category_id = ? WHERE id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setPreparedStatementForSave(ps, video22162006);
            ps.setLong(7, video22162006.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM videos WHERE id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Video_22162006 findById(String id) {
        String query = "SELECT * FROM videos WHERE id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, Long.parseLong(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Video_22162006> findAll() {
        List<Video_22162006> video22162006s = new ArrayList<>();
        String query = "SELECT * FROM videos";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                video22162006s.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video22162006s;
    }

    @Override
    public Long getLatestBookId() {
        Long videoId = null;
        String query = "SELECT MAX(id) AS id FROM videos";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                videoId = rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videoId;
    }

    @Override
    public void insertBookAuthorRelation(Long bookId, Long authorId) {
        String query = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, bookId);
            ps.setLong(2, authorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAuthorsByBookId(Long bookId) {
        String query = "DELETE FROM book_author WHERE book_id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Video_22162006> findByCategoryId(int categoryId, int page, int pageSize) {
        List<Video_22162006> video22162006s = new ArrayList<>();
        String query = "SELECT * FROM videos WHERE category_id = ? LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, pageSize);
            ps.setInt(3, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    video22162006s.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video22162006s;
    }

    @Override
    public int countByCategoryId(int categoryId) {
        String query = "SELECT COUNT(*) FROM videos WHERE category_id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}