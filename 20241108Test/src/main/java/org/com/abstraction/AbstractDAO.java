package org.com.abstraction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.com.config.JPAConfig;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected final EntityManager entityManager;
    protected final Class<T> entityClass;

    protected AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManager = JPAConfig.getEntityManager();
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(query, entityClass).getResultList();
    }

    @Override
    public void save(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(ID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T entity = findById(id);
            if (entity != null) {
                entityManager.remove(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}