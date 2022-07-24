package com.vti.rw41.utils;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import com.vti.rw41.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static SessionFactory sessionFactory = null;
    private static Session session = null;

    public static Session getSession() {
        if (session != null) {
            return session;
        }

        session = getSessionFactory().openSession();

        return session;
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory != null) {
            return sessionFactory;
        }

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(StudentEntity.class)
                .addAnnotatedClass(BillDetail.class)
                .addAnnotatedClass(CategoryEntity.class);

        EntityScanner.scanPackages("com.vti.rw41.entity.department")
                .addTo(configuration);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(registry);
        return sessionFactory;
    }
}
