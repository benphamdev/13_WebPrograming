package org.com.service;

import org.com.entity.Comment;

import java.util.List;

public interface ICommentService {
    void save(Comment comment);

    List<Comment> findAllWithUser();
}