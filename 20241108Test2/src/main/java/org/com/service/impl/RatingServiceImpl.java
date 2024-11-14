package org.com.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.com.config.JPAConfig;
import org.com.entity.Rating;
import org.com.service.IRatingService;

import java.util.List;

public class RatingServiceImpl implements IRatingService {
    private final EntityManager entityManager = JPAConfig.getEntityManager();

    @Override
    public void save(Rating rating) {
        entityManager.getTransaction().begin();
        entityManager.persist(rating);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Rating> findAllWithUser() {
        TypedQuery<Rating> query = entityManager.createQuery(
                "SELECT c FROM Rating c JOIN FETCH c.user", Rating.class);
        return query.getResultList();
    }
}