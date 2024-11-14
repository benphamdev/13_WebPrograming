package org.com.service;

import org.com.entity.Author;

import java.util.List;

public interface IAuthorService {
    void insert(Author author);

    void update(Author author);

    void delete(Long cateid) throws Exception;

    Author findById(Long cateid);

    List<Author> findAll();

    List<Author> findByCategoryname(String catname);

    List<Author> findAuthorByBookId(Long bookId);


    List<Author> findAll(int page, int pagesize);

    int count();

}
