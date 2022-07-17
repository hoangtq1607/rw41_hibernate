package com.vti.rw41.utils;

import com.vti.rw41.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory != null) {
            return sessionFactory;
        }

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(ProductEntity.class);
        configuration.addAnnotatedClass(TestTable.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(BillDetail.class);
        configuration.addAnnotatedClass(CategoryEntity.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(registry);
        return sessionFactory;
    }
}
