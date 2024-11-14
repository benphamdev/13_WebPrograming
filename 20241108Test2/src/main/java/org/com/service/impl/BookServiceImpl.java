package org.com.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.com.config.JPAConfig;
import org.com.dao.IBookDao;
import org.com.dao.impl.BookDao;
import org.com.entity.Book;
import org.com.service.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    private final IBookDao newsDao = new BookDao(Book.class);
    private final EntityManager entityManager = JPAConfig.getEntityManager();

    @Override
    public void insert(Book video) {
        newsDao.save(video);
    }

    @Override
    public void update(Book video) {
        newsDao.update(video);
    }

    @Override
    public void delete(String videoId) throws Exception {
        newsDao.delete(Long.parseLong(videoId));
    }

    @Override
    public Book findById(String videoId) {
//        return newsDao.findById(videoId);
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT n FROM Book n LEFT JOIN FETCH n.ratings WHERE n.id = :id", Book.class);
        query.setParameter("id", videoId);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        return newsDao.findAll();
    }

    @Override
    public List<Book> findAll(int page, int pagesize) {
        return newsDao.findAll(page, pagesize);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return newsDao.findByTitle(title);
    }

    @Override
    public List<Book> searchPaginated(String title, int page, int pageSize) {
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
