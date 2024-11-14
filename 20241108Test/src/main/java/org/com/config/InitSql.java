package org.com.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitSql implements ServletContextListener {

    private EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            emf = Persistence.createEntityManagerFactory("dataSource");
            EntityManager em = emf.createEntityManager();

            // Check if data already exists
            Long count = em.createQuery("SELECT COUNT(r) FROM Role r", Long.class)
                           .getSingleResult();
            if (count > 0) {
                em.close();
                return;
            }

            em.getTransaction().begin();

            String[] sqlStatements = {
                    // Categories
                    "INSERT INTO categories (code, name, created_date) VALUES " +
                            "('thoi-su', 'Thời sự', CURRENT_TIMESTAMP), " +
                            "('the-thao', 'Thể thao', CURRENT_TIMESTAMP), " +
                            "('chinh-tri', 'Chính trị', CURRENT_TIMESTAMP), " +
                            "('giao-duc', 'Giáo dục', CURRENT_TIMESTAMP)",

                    // Roles
                    "INSERT INTO roles (name, code, created_date) VALUES " +
                            "('ADMIN', 'ADMIN', CURRENT_TIMESTAMP), " +
                            "('USER', 'USER', CURRENT_TIMESTAMP)",

                    // Users
                    "INSERT INTO users (username, password, full_name, status, created_date) VALUES " +
                            "('admin', '123456', 'Administrator', 'ACTIVE', CURRENT_TIMESTAMP), " +
                            "('user', '123456', 'Regular User', 'ACTIVE', CURRENT_TIMESTAMP)",

                    // User Roles
                    "INSERT INTO user_role (user_id, role_id) VALUES " +
                            "((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE code = 'ADMIN')), " +
                            "((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE code = 'USER'))"
            };

            try {
                for (String sql : sqlStatements) {
                    em.createNativeQuery(sql).executeUpdate();
                }
                em.getTransaction().commit();
                System.out.println("Database initialized");
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Error during database initialization" + e.getMessage());
                throw e;
            } finally {
                em.close();
            }
        } catch (Exception e) {
            System.out.println("Error during database initialization" + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("Entity Manager Factory closed");
        }
    }
}