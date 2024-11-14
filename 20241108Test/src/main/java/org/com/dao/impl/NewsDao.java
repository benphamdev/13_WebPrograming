package org.com.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.com.config.JPAConfig;
import org.com.dao.INewsDao;
import org.com.entity.News;

import java.util.List;

public class NewsDao implements INewsDao {
    @Override
    public void insert(News video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            //gọi phuong thức để insert, update, delete
            enma.persist(video);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(News video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            //gọi phuong thức để insert, update, delete
            enma.merge(video);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(String videoId) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            //gọi phuong thức để insert, update, delete
            News video = enma.find(News.class, videoId);
            if (video != null) {
                enma.remove(video);
            } else {
                throw new Exception("Không tìm thấy");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public News findById(String videoId) {
        EntityManager enma = JPAConfig.getEntityManager();
        News video = enma.find(News.class, videoId);
        return video;
    }

    @Override
    public List<News> findByTitle(String title) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM News v WHERE v.title like :title";
        TypedQuery<News> query = enma.createQuery(jpql, News.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    @Override
    public List<News> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<News> query = enma.createNamedQuery("News.findAll", News.class);
        return query.getResultList();
    }

    @Override
    public List<News> findAll(int page, int pagesize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<News> query = enma.createNamedQuery("News.findAll", News.class);
        query.setFirstResult((page - 1) * pagesize);
        query.setMaxResults(pagesize);
        var ans = query.getResultList();

        return ans;
    }

    @Override
    public List<News> searchPaginated(String title, int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM News v WHERE v.title like :title";
        TypedQuery<News> query = enma.createQuery(jpql, News.class);
        query.setParameter("title", "%" + title + "%");
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT count(v) FROM News v";
        Query query = enma.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
}
