package org.com.service;

import org.com.entity.Category_22162006;

import java.util.List;

public interface ICategoryService_22162006 {
    void insert(Category_22162006 category22162006);

    void update(Category_22162006 category22162006);

    void delete(Long cateid) throws Exception;

    Category_22162006 findById(Long cateid);

    List<Category_22162006> findAll();

    List<Category_22162006> findByCategoryname(String catname);

    List<Category_22162006> findCategoriesByVideoId(Long bookId);

    List<Category_22162006> findAll(int page, int pagesize);

    int count();

}
