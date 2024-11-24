package org.com.service.impl;

import org.com.dao.IVideoDao_22162006;
import org.com.dao.impl.VideoDao_22162006;
import org.com.entity.Video_22162006;
import org.com.service.IVideoService_22162006;

import java.util.List;

public class VideoService_22162006Impl implements IVideoService_22162006 {
    private final IVideoDao_22162006 videoDao = new VideoDao_22162006();

    @Override
    public void insert(Video_22162006 video22162006) {
        videoDao.insert(video22162006);
    }

    @Override
    public void update(Video_22162006 video22162006) {
        videoDao.update(video22162006);
    }

    @Override
    public void delete(String bookId) throws Exception {
        videoDao.delete(bookId);
    }

    @Override
    public Video_22162006 findById(String bookId) {
        return videoDao.findById(bookId);
    }

    @Override
    public List<Video_22162006> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Video_22162006> findAll(int page, int pageSize) {
        return videoDao.findAll(page, pageSize);
    }

    @Override
    public List<Video_22162006> findByTitle(String title) {
        return videoDao.findByTitle(title);
    }

    @Override
    public List<Video_22162006> searchPaginated(String title, int page, int pageSize) {
        return videoDao.searchPaginated(title, page, pageSize);
    }

    @Override
    public int countByTitle(String title) {
        return 0;
    }

    @Override
    public int count() {
        return videoDao.count();
    }

    @Override
    public List<Video_22162006> findByCategoryId(int categoryId, int page, int pageSize) {
        return videoDao.findByCategoryId(categoryId, page, pageSize);
    }

    @Override
    public int countByCategoryId(int categoryId) {
        return videoDao.countByCategoryId(categoryId);

    }

    @Override
    public void insertBookAuthorRelation(Long bookId, Long authorId) {
        videoDao.insertBookAuthorRelation(bookId, authorId);
    }

    @Override
    public void deleteAuthorsByBookId(Long bookId) {
        videoDao.deleteAuthorsByBookId(bookId);
    }
}