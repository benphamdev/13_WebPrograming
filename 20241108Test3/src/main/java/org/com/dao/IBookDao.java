package org.com.dao;

import org.com.entity.Book;

import java.util.List;

public interface IBookDao  {
    List<Book> findByTitle(String title);

    List<Book> findAll(int page, int pagesize);

    List<Book> searchPaginated(String title, int page, int pageSize);

    int count();

    void insert(Book video);

    void update(Book video);

    void delete(String videoId) throws Exception;

    Book findById(String videoId);

    List<Book> findAll();

    Long getLatestBookId();
    void insertBookAuthorRelation(Long bookId, Long authorId);

    void deleteAuthorsByBookId(Long bookId);
}
