package org.example.com.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.com.config.JPAConfig;
import org.example.com.dao.IVideoDao;
import org.example.com.entity.Video;

import java.util.List;

public class VideoDao implements IVideoDao {
    @Override
    public void insert(Video video) {
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
    public void update(Video video) {
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
            Video video = enma.find(Video.class, videoId);
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
    public Video findById(String videoId) {
        EntityManager enma = JPAConfig.getEntityManager();
        Video video = enma.find(Video.class, videoId);
        return video;
    }

    @Override
    public List<Video> findByTitle(String title) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title like :title";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    @Override
    public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findAll(int page, int pagesize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        query.setFirstResult(page * pagesize);
        query.setMaxResults(pagesize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT count(v) FROM Video v";
        Query query = enma.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
}
