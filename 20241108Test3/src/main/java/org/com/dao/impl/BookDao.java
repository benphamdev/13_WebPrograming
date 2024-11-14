package org.com.dao.impl;

import org.com.config.DBConnect;
import org.com.dao.IBookDao;
import org.com.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {

    protected Book mapResultSetToEntity(ResultSet rs) throws SQLException {
        return Book.builder()
                .id(rs.getLong("id"))
                .isbn(rs.getString("isbn"))
                .title(rs.getString("title"))
                .publisher(rs.getString("publisher"))
                .price(rs.getDouble("price"))
                .description(rs.getString("description"))
                .publishDate(rs.getDate("publish_date"))
                .coverImage(rs.getString("cover_image"))
                .quantity(rs.getInt("quantity"))
                .build();
    }

    protected void setPreparedStatementForSave(PreparedStatement ps, Book book) throws SQLException {
        ps.setString(1, book.getIsbn());
        ps.setString(2, book.getTitle());
        ps.setString(3, book.getPublisher());
        ps.setDouble(4, book.getPrice());
        ps.setString(5, book.getDescription());
        ps.setDate(6, new java.sql.Date(book.getPublishDate().getTime()));
        ps.setString(7, book.getCoverImage());
        ps.setInt(8, book.getQuantity());
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book WHERE title LIKE ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + title + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findAll(int page, int pageSize) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> searchPaginated(String title, int page, int pageSize) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book WHERE title LIKE ? LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, pageSize);
            ps.setInt(3, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM book";
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
    public void insert(Book book) {
        String query = "INSERT INTO book (isbn, title, publisher, price, description, publish_date, cover_image, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setPreparedStatementForSave(ps, book);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    book.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        String query = "UPDATE book SET isbn = ?, title = ?, publisher = ?, price = ?, description = ?, publish_date = ?, cover_image = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setPreparedStatementForSave(ps, book);
            ps.setLong(9, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM book WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book findById(String id) {
        String query = "SELECT * FROM book WHERE id = ?";
        try (Connection conn = DBConnect.getConnection();
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
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                books.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Long getLatestBookId() {
        Long bookId = null;
        String query = "SELECT MAX(id) AS id FROM book";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                bookId = rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookId;
    }

    @Override
    public void insertBookAuthorRelation(Long bookId, Long authorId) {
        String query = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";
        try (Connection conn = DBConnect.getConnection();
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
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
