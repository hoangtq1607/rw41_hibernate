package com.vti.rw41;

import com.vti.rw41.entity.Product;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        testInsert();
//        testSelectAndUpdate();
//        testSelectAndDelete();

        List<Product> result = ProductRepository.findByName("SP1");

        for (Product product : result) {
            System.out.println(product);
        }
    }

    static void testSelectAndDelete() {
        Product product = ProductRepository.findById(3);
        if (product != null) {
            ProductRepository.delete(product);
        }
    }

    static void testSelectAndUpdate() {
        Product product = ProductRepository.findById(3);
        if (product != null) {
            product.setName("AAAAAAAAAAAAAAAAA");
            ProductRepository.saveOrUpdate(product);
        }
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
