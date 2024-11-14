package org.com.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.com.config.JPAConfig;
import org.com.dao.IUserDao;
import org.com.entity.User;

import java.util.List;

public class UserDao implements IUserDao {

    @Override
    public User findByUsername(String username) {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public boolean checkExistUsername(String username) {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        Long count = query.getSingleResult();
        return count > 0;
    }

//    @Override
//    public boolean checkExistEmail(String email) {
//        EntityManager em = JPAConfig.getEntityManager();
//        TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
//        query.setParameter("email", email);
//        Long count = query.getSingleResult();
//        return count > 0;
//    }

    @Override
    public void insert(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        }
    }

    @Override
    public boolean changePassword(String email, String newEncodedPassword) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", email);
            User user = query.getSingleResult();
            if (user != null) {
                user.setPassword(newEncodedPassword);
                em.merge(user);
                trans.commit();
                return true;
            } else {
                trans.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            return false;
        }
    }

//    @Override
//    public boolean checkExistPhone(String phone) {
//        EntityManager em = JPAConfig.getEntityManager();
//        TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.phone = :phone", Long.class);
//        query.setParameter("phone", phone);
//        Long count = query.getSingleResult();
//        return count > 0;
//    }
}