package org.com.service;

import org.com.entity.Video_22162006;

import java.util.List;

public interface IVideoService_22162006 {
    void insert(Video_22162006 video22162006);

    void update(Video_22162006 video22162006);

    void delete(String videoId) throws Exception;

    Video_22162006 findById(String videoId);

    List<Video_22162006> findAll();

    List<Video_22162006> findAll(int page, int pageSize);

    List<Video_22162006> findByTitle(String title);

    List<Video_22162006> searchPaginated(String title, int page, int pageSize);

    int countByTitle(String title);

    int count();

    List<Video_22162006> findByCategoryId(int categoryId, int page, int pageSize);

    int countByCategoryId(int categoryId);

    void insertBookAuthorRelation(Long bookId, Long authorId);

    void deleteAuthorsByBookId(Long bookId);
}
