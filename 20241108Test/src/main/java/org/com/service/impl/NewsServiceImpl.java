package org.com.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.com.config.JPAConfig;
import org.com.dao.INewsDao;
import org.com.dao.impl.NewsDao;
import org.com.entity.News;
import org.com.service.INewsService;

import java.util.List;

public class NewsServiceImpl implements INewsService {
    private final INewsDao newsDao = new NewsDao();
    private final EntityManager entityManager = JPAConfig.getEntityManager();

    @Override
    public void insert(News video) {
        newsDao.insert(video);
    }

    @Override
    public void update(News video) {
        newsDao.update(video);
    }

    @Override
    public void delete(String videoId) throws Exception {
        newsDao.delete(videoId);
    }

    @Override
    public News findById(String videoId) {
//        return newsDao.findById(videoId);
        TypedQuery<News> query = entityManager.createQuery(
                "SELECT n FROM News n LEFT JOIN FETCH n.comments WHERE n.id = :id", News.class);
        query.setParameter("id", videoId);
        return query.getSingleResult();
    }

    @Override
    public List<News> findByTitle(String title) {
        return newsDao.findByTitle(title);
    }

    @Override
    public List<News> findAll() {
        return newsDao.findAll();
    }

    @Override
    public List<News> findAll(int page, int pagesize) {
        return newsDao.findAll(page, pagesize);
    }

    @Override
    public List<News> searchPaginated(String title, int page, int pageSize) {
        return newsDao.searchPaginated(title, page, pageSize);
    }

    @Override
    public int countByTitle(String title) {
        return 0;
    }

    @Override
    public int count() {
        return newsDao.count();
    }
}
