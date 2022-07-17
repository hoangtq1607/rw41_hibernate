package com.vti.rw41.repository;

import com.vti.rw41.entity.CategoryEntity;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class CategoryRepository {

    public static Optional<CategoryEntity> findByName(String name) {

        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Query<CategoryEntity> query = session.createQuery(
                "FROM CategoryEntity where name = :name", CategoryEntity.class)
                .setParameter("name", name);

        return query.getResultStream().findFirst();
    }

}
