package com.vti.rw41.utils;

import com.vti.rw41.entity.BillDetail;
import com.vti.rw41.entity.ProductEntity;
import com.vti.rw41.entity.Student;
import com.vti.rw41.entity.TestTable;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    public static SessionFactory getSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(ProductEntity.class);
        configuration.addAnnotatedClass(TestTable.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(BillDetail.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(registry);

    }
}
