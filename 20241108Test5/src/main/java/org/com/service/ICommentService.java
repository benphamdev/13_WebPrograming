package org.com.service;

import org.com.entity.Comment;

import java.util.List;

public interface ICommentService {
    void save(Comment share22162006);

    List<Comment> findAllWithUser();

    List<Comment> findAllWithVideoId(long bookId);

    long countLikesByProductId(long productId); // Đếm số lượng likes theo productId

    void update(Comment comment);

    void delete(Long id);

    Comment findById(Long id);

    List<Comment> findAllWithProductId(long productId);
}
