package org.com.dao;

import org.com.abstraction.GenericDAO;
import org.com.entity.Category;

import java.util.List;

public interface ICategoryDao extends GenericDAO<Category, Integer> {
    List<Category> findByCategoryName(String catname);

    List<Category> findCategoriesByVideoId(Long bookId);

    List<Category> findAll(int page, int pagesize);

    int count();

    void save(Category category);

    void update(Category category);

    void delete(int cateid) throws Exception;

    Category findById(int cateid);

    List<Category> findAll();
}