package org.com.dao;

import org.com.abstraction.GenericDAO;
import org.com.entity.Product;

import java.util.List;

public interface IProductDao extends GenericDAO<Product, Long> {
    List<Product> findByTitle(String title);

    List<Product> findAll(int page, int pagesize);

    List<Product> searchPaginated(String title, int page, int pageSize);

    int count();

    void insert(Product Product);

//    void update(Product Product);

//    void delete(String videoId) throws Exception;

//    Product findById(String videoId);

//    List<Product> findAll();

    Long getLatestBookId();

    void insertBookAuthorRelation(Long bookId, Long authorId);

    void deleteAuthorsByBookId(Long bookId);

    List<Product> findByCategoryId(int categoryId, int page, int pageSize);

    int countByCategoryId(int categoryId);

    List<Product> findBySellerId(Long sellerId);

    int countBySellerId(Long sellerId);
}
