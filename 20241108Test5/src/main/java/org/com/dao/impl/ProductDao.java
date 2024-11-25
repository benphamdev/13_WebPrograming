package org.com.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.com.abstraction.AbstractDAO;
import org.com.config.JPAConfig;
import org.com.dao.IProductDao;
import org.com.entity.Product;

import java.util.List;

public class ProductDao extends AbstractDAO<Product, Long> implements IProductDao {

    public ProductDao(Class<Product> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Product> findByTitle(String title) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Product v WHERE v.productName LIKE :title";
        TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    @Override
    public List<Product> findAll(int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Product> searchPaginated(String title, int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Product v WHERE v.productName LIKE :title";
        TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
        query.setParameter("title", "%" + title + "%");
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(v) FROM Product v";
        Query query = enma.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public void insert(Product Product) {
        EntityManager enma = JPAConfig.getEntityManager();
        var trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(Product);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        }
    }

    @Override
    public void update(Product Product) {
        EntityManager enma = JPAConfig.getEntityManager();
        var trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(Product);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        }
    }

//    @Override
//    public Product findById(String id) {
//        EntityManager enma = JPAConfig.getEntityManager();
//        return enma.find(Product.class, Long.parseLong(id));
//    }

    @Override
    public Long getLatestBookId() {
        return 1L;
    }

    @Override
    public void insertBookAuthorRelation(Long bookId, Long authorId) {

    }

    @Override
    public void deleteAuthorsByBookId(Long bookId) {

    }

    @Override
    public List<Product> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
        return query.getResultList();
    }

//    @Override
//    public Long getLatestVideoId() {
//        EntityManager enma = JPAConfig.getEntityManager();
//        String jpql = "SELECT MAX(v.id) FROM Product v";
//        Query query = enma.createQuery(jpql);
//        return (Long) query.getSingleResult();
//    }

    @Override
    public List<Product> findByCategoryId(int categoryId, int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Product v WHERE v.id = :categoryId";
        TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
        query.setParameter("categoryId", categoryId);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int countByCategoryId(int categoryId) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(v) FROM Product v WHERE v.id = :categoryId";
        Query query = enma.createQuery(jpql);
        query.setParameter("categoryId", categoryId);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public List<Product> findBySellerId(Long sellerId) {
        int page = 1, pageSize = 5;
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql =
                "SELECT p FROM Product p WHERE p.seller.id = :sellerId"; // Giả sử Product có thuộc tính seller là một đối tượng User
        TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
        query.setParameter("sellerId", sellerId);
        query.setFirstResult((page - 1) * pageSize); // Thiết lập phân trang
        query.setMaxResults(pageSize); // Giới hạn số lượng sản phẩm trên mỗi trang
        return query.getResultList();
    }

    @Override
    public int countBySellerId(Long sellerId) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql =
                "SELECT COUNT(v) FROM Product v WHERE v.seller.id = :sellerId"; // Giả sử Product có thuộc tính seller là một đối tượng User
        Query query = enma.createQuery(jpql);
        query.setParameter("sellerId", sellerId);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public List<Product> getTop10BestSellers() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT p FROM Product p ORDER BY p.nSold DESC";
            TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
            query.setMaxResults(10); // Lấy 10 sản phẩm
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Product> getTop10MostViewed() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT p FROM Product p ORDER BY p.nVisit DESC";
            TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
            query.setMaxResults(10); // Lấy 10 sản phẩm
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Product> getTop10MostLiked() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = """
                    SELECT p FROM Product p
                    JOIN p.comments c
                    GROUP BY p.id
                    ORDER BY SUM(c.rating) DESC
                    """;
            TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
            query.setMaxResults(10); // Lấy 10 sản phẩm
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
}
