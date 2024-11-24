package org.com.service.impl;

import org.com.dao.IProductDao;
import org.com.dao.impl.ProductDao;
import org.com.entity.Product;
import org.com.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    private final IProductDao videoDao = new ProductDao(Product.class);

    @Override
    public void insert(Product video) {
        videoDao.insert(video);
    }

    @Override
    public void update(Product video) {
        videoDao.update(video);
    }

    @Override
    public void delete(String bookId) throws Exception {
        videoDao.delete(Long.parseLong(bookId));
    }

    @Override
    public Product findById(String bookId) {
        return videoDao.findById(Long.parseLong(bookId));
    }

    @Override
    public List<Product> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Product> findAll(int page, int pageSize) {
        return videoDao.findAll(page, pageSize);
    }

    @Override
    public List<Product> findByTitle(String title) {
        return videoDao.findByTitle(title);
    }

    @Override
    public List<Product> searchPaginated(String title, int page, int pageSize) {
        return videoDao.searchPaginated(title, page, pageSize);
    }

    @Override
    public int countByTitle(String title) {
        return 0;
    }

    @Override
    public int count() {
        return videoDao.count();
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, int page, int pageSize) {
        return videoDao.findByCategoryId(categoryId, page, pageSize);
    }

    @Override
    public int countByCategoryId(int categoryId) {
        return videoDao.countByCategoryId(categoryId);

    }

    @Override
    public void insertBookAuthorRelation(Long bookId, Long authorId) {
        videoDao.insertBookAuthorRelation(bookId, authorId);
    }

    @Override
    public void deleteAuthorsByBookId(Long bookId) {
        videoDao.deleteAuthorsByBookId(bookId);
    }

    @Override
    public List<Product> findBySellerId(Long sellerId) {
        return videoDao.findBySellerId(sellerId);
    }

    @Override
    public int countBySellerId(Long sellerId) {
        return videoDao.countBySellerId(sellerId);
    }
}