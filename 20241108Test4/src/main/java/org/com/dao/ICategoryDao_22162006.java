package org.com.dao;

import org.com.entity.Category_22162006;

import java.util.List;

public interface ICategoryDao_22162006 {
    List<Category_22162006> findByCategoryName(String catname);

    List<Category_22162006> findCategoriesByVideoId(Long bookId);

    List<Category_22162006> findAll(int page, int pagesize);

    int count();

    void save(Category_22162006 category22162006);

    void update(Category_22162006 category22162006);

    void delete(int cateid) throws Exception;

    Category_22162006 findById(int cateid);

    List<Category_22162006> findAll();
}