package org.com.dao.impl;

import org.com.config.DBConnect_22162006;
import org.com.dao.ICategoryDao_22162006;
import org.com.entity.Category_22162006;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao_22162006 implements ICategoryDao_22162006 {

    protected Category_22162006 mapResultSetToEntity(ResultSet rs) throws SQLException {
        return Category_22162006.builder()
                                .id(rs.getInt("id"))
                                .categoryName(rs.getString("category_name"))
                                .build();
    }

    // Helper method for setting PreparedStatement parameters for save/update
    protected void setPreparedStatementForSave(
            PreparedStatement ps, Category_22162006 category22162006
    ) throws SQLException {
        ps.setString(1, category22162006.getCategoryName());
    }

    @Override
    public List<Category_22162006> findAll(int page, int pageSize) {
        List<Category_22162006> categories = new ArrayList<>();
        String query = "SELECT * FROM category LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, page * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    categories.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM category22162006";
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
    public void save(Category_22162006 category22162006) {
        String query = "INSERT INTO category (category_name) VALUES (?)";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setPreparedStatementForSave(ps, category22162006);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category_22162006 category22162006) {
        String query = "UPDATE category SET category_name = ? WHERE id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setPreparedStatementForSave(ps, category22162006);
            ps.setInt(2, category22162006.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String query = "DELETE FROM category WHERE id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Category not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error deleting category22162006", e);
        }
    }

    @Override
    public Category_22162006 findById(int id) {
        String query = "SELECT * FROM category WHERE id = ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
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
    public List<Category_22162006> findAll() {
        List<Category_22162006> categories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categories.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category_22162006> findByCategoryName(String catname) {
        List<Category_22162006> categories = new ArrayList<>();
        String query = "SELECT * FROM category WHERE category_name LIKE ?";
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + catname + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    categories.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category_22162006> findCategoriesByVideoId(Long videoId) {
        List<Category_22162006> categories = new ArrayList<>();
        String query = """
                SELECT c.id, c.category_name
                FROM category c
                INNER JOIN videos v ON c.id = v.category_id
                WHERE v.id = ?
                """;
        try (Connection conn = DBConnect_22162006.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, videoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    categories.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding categories by video ID: " + videoId, e);
        }
        return categories;
    }
}