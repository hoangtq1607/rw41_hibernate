package com.vti.rw41.repository;

import com.vti.rw41.entity.ProductEntity;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductRepository {

    public static List<ProductEntity> findByName(String name) {

        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Query<ProductEntity> query = session.createQuery("FROM ProductEntity p join FETCH p.category WHERE p.productName = :productName", ProductEntity.class);
        query.setParameter("productName", name);

        return query.getResultList();

    }

    public static ProductEntity findProductById(Integer id) {

        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        return session.find(ProductEntity.class, id);

    }

    public static ProductEntity saveOrUpdate(ProductEntity productEntity) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();

        session.saveOrUpdate(productEntity);

        session.getTransaction().commit();
        return productEntity;
    }

    public static ProductEntity delete(ProductEntity productEntity) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();
        session.delete(productEntity);

        session.getTransaction().commit();
        return productEntity;
    }

    public static List<ProductEntity> findAllProduct() {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        return session.createQuery("from ProductEntity", ProductEntity.class)
                .getResultList();
    }
}
