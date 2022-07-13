package com.vti.rw41.repository;

import com.vti.rw41.entity.Product;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductRepository {

    public static List<Product> findByName(String name) {

        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Query<Product> query = session.createQuery("FROM Product WHERE name = :productName", Product.class);
        query.setParameter("productName", name);

        return query.getResultList();

    }

    public static Product findById(Integer id) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        return session.find(Product.class, id);

    }

    public static Product saveOrUpdate(Product product) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();
        session.saveOrUpdate(product);

        session.getTransaction().commit();
        return product;
    }

    public static Product delete(Product product) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();
        session.delete(product);

        session.getTransaction().commit();
        return product;
    }

}
