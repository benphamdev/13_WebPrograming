package org.com.dao.impl;

import org.com.config.DBConnect;
import org.com.dao.IAuthorDao;
import org.com.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao implements IAuthorDao {

    protected Author mapResultSetToEntity(ResultSet rs) throws SQLException {
        return Author.builder()
                .id(rs.getLong("id"))
                .authorName(rs.getString("author_name"))
                .build();
    }

    // Helper method for setting PreparedStatement parameters for save/update
    protected void setPreparedStatementForSave(PreparedStatement ps, Author author) throws SQLException {
        ps.setString(1, author.getAuthorName());
    }

    @Override
    public List<Author> findByCategoryname(String authorName) {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM author WHERE author_name LIKE ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + authorName + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authors.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<Author> findAll(int page, int pageSize) {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM author LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, page * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authors.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM author";
        try (Connection conn = DBConnect.getConnection();
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
    public void save(Author author) {
        String query = "INSERT INTO author (author_name) VALUES (?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setPreparedStatementForSave(ps, author);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Author author) {
        String query = "UPDATE author SET author_name = ? WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setPreparedStatementForSave(ps, author);
            ps.setLong(2, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String query = "DELETE FROM author WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Author not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error deleting author", e);
        }
    }

    @Override
    public Author findById(int id) {
        String query = "SELECT * FROM author WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
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
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM author";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                authors.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<Author> findAuthorByBookId(Long bookId) {
        List<Author> authors = new ArrayList<>();
        String query = """
            SELECT a.id, a.author_name, a.date_of_birth
            FROM author a
            INNER JOIN book_author ba ON a.id = ba.author_id
            WHERE ba.book_id = ?
            """;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setLong(1, bookId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authors.add(mapResultSetToEntity(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding authors by book ID: " + bookId, e);
        }

        return authors;
    }

    @Override
    public List<Author> findAuthorsByBookId(Long bookId) {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT a.author_id, a.author_name, a.date_of_birth " +
                "FROM author a " +
                "JOIN book_author ba ON a.author_id = ba.author_id " +
                "WHERE ba.bookid = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Author author = new Author();
                    author.setId(rs.getLong("author_id"));
                    author.setAuthorName(rs.getString("author_name"));
                    author.setDateOfBirth(rs.getDate("date_of_birth"));
                    authors.add(author);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
