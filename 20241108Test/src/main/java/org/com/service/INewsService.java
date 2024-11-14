package org.com.service;

import org.com.entity.News;

import java.util.List;

public interface INewsService {
    void insert(News video);

    void update(News video);

    void delete(String videoId) throws Exception;

    News findById(String videoId);

    List<News> findByTitle(String title);

    List<News> findAll();

    List<News> findAll(int page, int pageSize);

    List<News> searchPaginated(String title, int page, int pageSize);

    int countByTitle(String title);

    int count();
}
