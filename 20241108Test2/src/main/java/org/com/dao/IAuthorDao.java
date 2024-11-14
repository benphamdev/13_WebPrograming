package org.com.dao;

import org.com.abstraction.GenericDAO;
import org.com.entity.Author;

import java.util.List;

public interface IAuthorDao extends GenericDAO<Author, Long> {
    List<Author> findByCategoryname(String catname);

    List<Author> findAll(int page, int pagesize);

    int count();

    //    void save(Author author);
//
//    void update(Author author);
//
//    void delete(int cateid) throws Exception;
//
//    Author findById(int cateid);
//
//    List<Author> findAll();
}