package org.com.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.com.abstraction.AbstractDAO;
import org.com.config.JPAConfig;
import org.com.dao.ICategoryDao;
import org.com.entity.Category;

import java.util.List;

public class CategoryDao extends AbstractDAO<Category, Integer> implements ICategoryDao {

    public CategoryDao(Class<Category> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Category> findAll(int page, int pageSize) {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(c) FROM Category c";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public void save(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int cateid) throws Exception {

    }

    @Override
    public void delete(Integer id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Category category = em.find(Category.class, id);
            if (category != null) {
                em.remove(category);
            } else {
                throw new Exception("Category not found");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public Category findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public List<Category> findByCategoryName(String catname) {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c WHERE c.categoryName LIKE :catname",
                Category.class
        );
        query.setParameter("catname", "%" + catname + "%");
        return query.getResultList();
    }

    @Override
    public List<Category> findCategoriesByVideoId(Long videoId) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = """
                    SELECT c FROM Category c
                    JOIN Video v ON c.id = v.category.id
                    WHERE v.id = :videoId
                """;
        TypedQuery<Category> query = em.createQuery(jpql, Category.class);
        query.setParameter("videoId", videoId);
        return query.getResultList();
    }
}
