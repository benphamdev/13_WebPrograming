package org.com.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.com.config.JPAConfig;
import org.com.entity.Cart;
import org.com.service.ICartService;

import java.util.List;

public class CartServiceImpl implements ICartService {

    @Override
    public void save(Cart share) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(share);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cart> findAllWithUser() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT s FROM Cart s JOIN FETCH s.user u";
        TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
        return query.getResultList();
    }

    @Override
    public List<Cart> findAllWithVideoId(long videoId) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT s FROM Cart s WHERE s.cartId = :videoId";
        TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
        query.setParameter("videoId", videoId);
        return query.getResultList();
    }
}
