package org.com.service.impl;

import org.com.dao.IAuthorDao;
import org.com.dao.impl.AuthorDao;
import org.com.entity.Author;
import org.com.service.IAuthorService;

import java.util.List;

public class AuthorServiceImpl implements IAuthorService {
    IAuthorDao categoryDao = new AuthorDao(Author.class);

    @Override
    public void insert(Author author) {
        categoryDao.save(author);
    }

    @Override
    public void update(Author author) {
        categoryDao.update(author);
    }

    @Override
    public void delete(Long cateid) throws Exception {
        categoryDao.delete(cateid);
    }

    @Override
    public Author findById(Long cateid) {
        return categoryDao.findById(cateid);
    }

    @Override
    public List<Author> findAll() {
        return categoryDao.findAll();
    }

//    @Override
//    public List<Author> findByCategoryname(String catname) {
//        return categoryDao.findByCategoryname(catname);
//    }
//
//    @Override
//    public List<Author> findAll(int page, int pagesize) {
//        return categoryDao.findAll(page, pagesize);
//    }
//
//    @Override
//    public int count() {
//        return categoryDao.count();
//    }
}
