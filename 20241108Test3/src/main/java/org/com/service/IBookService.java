package org.com.service;

import org.com.entity.Book;

import java.util.List;

public interface IBookService {
    void insert(Book video);

    void update(Book video);

    void delete(String videoId) throws Exception;

    Book findById(String videoId);

    List<Book> findAll();

    List<Book> findAll(int page, int pageSize);

    List<Book> findByTitle(String title);

    List<Book> searchPaginated(String title, int page, int pageSize);

    int countByTitle(String title);

    int count();
    Long getLatestBookId();

    void insertBookAuthorRelation(Long bookId, Long authorId);

    void deleteAuthorsByBookId(Long bookId);




}
