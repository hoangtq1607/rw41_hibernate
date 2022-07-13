package com.vti.rw41;

import com.vti.rw41.entity.Product;
import org.hibernate.Session;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        testSelectAndUpdate();
//        testSelectAndDelete();

    }

    static void testSelectAndDelete() {

        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();

        Product product = session.find(Product.class, 2);

        if (product != null) {
            session.delete(product);
        }

        session.getTransaction().commit();

    }

    static void testSelectAndUpdate() {

        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();

        Product product = session.find(Product.class, 2);

        if (product != null) {

            product.setPrice(99.9);
            product.setUpdatedDate(LocalDateTime.now());
            product.setName("San pham 1");

            session.saveOrUpdate(product);

        }

        session.getTransaction().commit();

        System.out.println(product);
    }

    // update product set name = 'updated name' where id = 1
    static void testInsert() {

        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();

        Product product = new Product();

        product.setName("San pham 1");
        product.setPrice(99.999);
        product.setUpdatedDate(LocalDateTime.now());
        product.setCreatedDate(LocalDateTime.now());

        session.saveOrUpdate(product); //save doi tuong vao db

        session.getTransaction().commit(); //commit transaction

    }

}
