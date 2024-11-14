package org.com.service.impl;

import org.com.dao.IBookDao;
import org.com.dao.impl.BookDao;
import org.com.entity.Book;
import org.com.service.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    private final IBookDao bookDao = new BookDao();

    @Override
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void delete(String bookId) throws Exception {
        bookDao.delete(bookId);
    }

    @Override
    public Book findById(String bookId) {
        return bookDao.findById(bookId);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findAll(int page, int pageSize) {
        return bookDao.findAll(page, pageSize);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookDao.findByTitle(title);
    }

    @Override
    public List<Book> searchPaginated(String title, int page, int pageSize) {
        return bookDao.searchPaginated(title, page, pageSize);
    }

    @Override
    public int countByTitle(String title) {
        return 0;
    }

    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public Long getLatestBookId() {
        return bookDao.getLatestBookId();
    }

    @Override
    public void insertBookAuthorRelation(Long bookId, Long authorId) {
        bookDao.insertBookAuthorRelation(bookId, authorId);
    }

    @Override
    public void deleteAuthorsByBookId(Long bookId) {
        bookDao.deleteAuthorsByBookId(bookId);
    }
}