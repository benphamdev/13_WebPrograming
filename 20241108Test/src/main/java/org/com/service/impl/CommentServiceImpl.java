package org.com.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.com.config.JPAConfig;
import org.com.entity.Comment;
import org.com.service.ICommentService;

import java.util.List;

public class CommentServiceImpl implements ICommentService {
    private final EntityManager entityManager = JPAConfig.getEntityManager();

    @Override
    public void save(Comment comment) {
        entityManager.getTransaction().begin();
        entityManager.persist(comment);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Comment> findAllWithUser() {
        TypedQuery<Comment> query = entityManager.createQuery(
                "SELECT c FROM Comment c JOIN FETCH c.user", Comment.class);
        return query.getResultList();
    }
}