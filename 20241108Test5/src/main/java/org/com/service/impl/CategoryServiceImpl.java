package org.com.service.impl;

import org.com.dao.ICategoryDao;
import org.com.dao.impl.CategoryDao;
import org.com.entity.Category;
import org.com.service.ICategoryService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    ICategoryDao categoryDao = new CategoryDao(Category.class);

    @Override
    public void insert(Category author) {
        categoryDao.save(author);
    }

    @Override
    public void update(Category author) {
        categoryDao.update(author);
    }

    @Override
    public void delete(Long cateid) throws Exception {
        categoryDao.delete(cateid.intValue());
    }

    @Override
    public Category findById(Long cateid) {
        return categoryDao.findById(cateid.intValue());
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> findByCategoryname(String catname) {
        return categoryDao.findByCategoryName(catname);
    }

    @Override
    public List<Category> findCategoriesByVideoId(Long bookId) {
        return categoryDao.findCategoriesByVideoId(bookId);
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        return categoryDao.findAll(page, pagesize);
    }

    @Override
    public int count() {
        return categoryDao.count();
    }
}
