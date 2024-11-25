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

    // Lấy 10 sản phẩm bán chạy nhất
    List<Product> getTop10BestSellers();

    // Lấy 10 sản phẩm được xem nhiều nhất
    List<Product> getTop10MostViewed();

    // Lấy 10 sản phẩm được yêu thích nhất (dựa trên tổng rating)
    List<Product> getTop10MostLiked();
}
