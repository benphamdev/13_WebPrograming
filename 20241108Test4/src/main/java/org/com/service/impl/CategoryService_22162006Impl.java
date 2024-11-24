package org.com.service.impl;

import org.com.dao.ICategoryDao_22162006;
import org.com.dao.impl.CategoryDao_22162006;
import org.com.entity.Category_22162006;
import org.com.service.ICategoryService_22162006;

import java.util.List;

public class CategoryService_22162006Impl implements ICategoryService_22162006 {
    ICategoryDao_22162006 categoryDao = new CategoryDao_22162006();

    @Override
    public void insert(Category_22162006 author) {
        categoryDao.save(author);
    }

    @Override
    public void update(Category_22162006 author) {
        categoryDao.update(author);
    }

    @Override
    public void delete(Long cateid) throws Exception {
        categoryDao.delete(cateid.intValue());
    }

    @Override
    public Category_22162006 findById(Long cateid) {
        return categoryDao.findById(cateid.intValue());
    }

    @Override
    public List<Category_22162006> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category_22162006> findByCategoryname(String catname) {
        return categoryDao.findByCategoryName(catname);
    }

    @Override
    public List<Category_22162006> findCategoriesByVideoId(Long bookId) {
        return categoryDao.findCategoriesByVideoId(bookId);
    }

    @Override
    public List<Category_22162006> findAll(int page, int pagesize) {
        return categoryDao.findAll(page, pagesize);
    }

    @Override
    public int count() {
        return categoryDao.count();
    }
}
