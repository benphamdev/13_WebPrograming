package org.com.dao;

import org.com.entity.Author;

import java.util.List;

public interface IAuthorDao {
    List<Author> findByCategoryname(String catname);

    List<Author> findAuthorByBookId(Long bookId);


    List<Author> findAll(int page, int pagesize);

    int count();

    void save(Author author);

    void update(Author author);

    void delete(int cateid) throws Exception;

    Author findById(int cateid);

    List<Author> findAll();

    List<Author> findAuthorsByBookId(Long bookId);
}