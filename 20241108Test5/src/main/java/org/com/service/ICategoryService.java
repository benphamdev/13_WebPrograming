package org.com.service;

import org.com.entity.Category;

import java.util.List;

public interface ICategoryService {
    void insert(Category category);

    void update(Category category);

    void delete(Long cateid) throws Exception;

    Category findById(Long cateid);

    List<Category> findAll();

    List<Category> findByCategoryname(String catname);

    List<Category> findCategoriesByVideoId(Long bookId);

    List<Category> findAll(int page, int pagesize);

    int count();

}
