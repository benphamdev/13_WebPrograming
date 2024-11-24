package org.com.service;

import org.com.entity.Product;

import java.util.List;

public interface IProductService {
    void insert(Product video);

    void update(Product video);

    void delete(String videoId) throws Exception;

    Product findById(String videoId);

    List<Product> findAll();

    List<Product> findAll(int page, int pageSize);

    List<Product> findByTitle(String title);

    List<Product> searchPaginated(String title, int page, int pageSize);

    int countByTitle(String title);

    int count();

    List<Product> findByCategoryId(int categoryId, int page, int pageSize);

    int countByCategoryId(int categoryId);

    void insertBookAuthorRelation(Long bookId, Long authorId);

    void deleteAuthorsByBookId(Long bookId);

    List<Product> findBySellerId(Long sellerId);

    int countBySellerId(Long sellerId);
}
