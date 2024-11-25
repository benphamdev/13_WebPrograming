package org.com.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.com.entity.Comment;
import org.com.service.ICommentService;

import java.util.List;

public class CommentServiceServiceImpl implements ICommentService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    @Override
    public void save(Comment share22162006) {

    }

    @Override
    public List<Comment> findAllWithUser() {
        return List.of();
    }

    @Override
    public List<Comment> findAllWithVideoId(long bookId) {
        return List.of();
    }

    @Override
    public long countLikesByProductId(long productId) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT COALESCE(SUM(c.rating), 0) FROM Comment c WHERE c.product.id = :productId";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("productId", productId);
            return query.getSingleResult(); // Trả về tổng số lượng like
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Comment comment) {
        
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Comment findById(Long id) {
        return null;
    }

    @Override
    public List<Comment> findAllWithProductId(long productId) {
        return List.of();
    }
}
