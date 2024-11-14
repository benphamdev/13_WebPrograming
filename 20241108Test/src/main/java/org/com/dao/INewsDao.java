package org.com.dao;

import org.com.entity.News;

import java.util.List;

public interface INewsDao {
    void insert(News video);

    void update(News video);

    void delete(String videoId) throws Exception;

    News findById(String videoId);

    List<News> findByTitle(String title);

    List<News> findAll();

    List<News> findAll(int page, int pagesize);

    List<News> searchPaginated(String title, int page, int pageSize);

    int count();
}
