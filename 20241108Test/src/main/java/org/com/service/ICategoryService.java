package org.com.service;

import org.com.entity.Category;

import java.util.List;

public interface ICategoryService {
    void insert(Category category);

    void update(Category category);

    void delete(int cateid) throws Exception;

    Category findById(int cateid);

    List<Category> findAll();

    List<Category> findByCategoryname(String catname);

    List<Category> findAll(int page, int pagesize);

    int count();
}
