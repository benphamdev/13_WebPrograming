package org.com.dao;

import org.com.entity.Video_22162006;

import java.util.List;

public interface IVideoDao_22162006 {
    List<Video_22162006> findByTitle(String title);

    List<Video_22162006> findAll(int page, int pagesize);

    List<Video_22162006> searchPaginated(String title, int page, int pageSize);

    int count();

    void insert(Video_22162006 video22162006);

    void update(Video_22162006 video22162006);

    void delete(String videoId) throws Exception;

    Video_22162006 findById(String videoId);

    List<Video_22162006> findAll();

    Long getLatestBookId();

    void insertBookAuthorRelation(Long bookId, Long authorId);

    void deleteAuthorsByBookId(Long bookId);

    List<Video_22162006> findByCategoryId(int categoryId, int page, int pageSize);

    int countByCategoryId(int categoryId);
}
