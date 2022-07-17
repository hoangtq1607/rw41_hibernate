package com.vti.rw41;

import com.vti.rw41.entity.BillDetail;
import com.vti.rw41.entity.ProductEntity;
import com.vti.rw41.entity.Student;
import com.vti.rw41.entity.TestTable;
import com.vti.rw41.enumurations.ProductStatus;
import com.vti.rw41.repository.ProductRepository;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        BillDetail billDetail = new BillDetail();
        billDetail.setBillId(1);
        billDetail.setQuantity(10);
        billDetail.setProductPrice(99.99);
        billDetail.setProductId(1);
        session.saveOrUpdate(billDetail);
        session.getTransaction().commit();
    }

    static void testSelectAndDelete() {
        ProductEntity productEntity = ProductRepository.findProductById(3);
        if (productEntity != null) {
            ProductRepository.delete(productEntity);
        }
    }

    static void testSelectAndUpdate() {
        ProductEntity productEntity = ProductRepository.findProductById(4);
        if (productEntity != null) {
            productEntity.setProductName("test 1234 test 1234test 1234test 1234test 1234test 1234test 1234test 1234test 1234test 1234");
            productEntity.setStatus(ProductStatus.INACTIVE);
            ProductRepository.saveOrUpdate(productEntity);
        }
    }

    // update product set name = 'updated name' where id = 1
    static void testInsert() {

        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();

        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductName("San pham 1");
        productEntity.setPrice(99.999);
        productEntity.setUpdatedDate(LocalDateTime.now());
        productEntity.setCreatedDate(LocalDateTime.now());

        session.saveOrUpdate(productEntity); //save doi tuong vao db

        session.getTransaction().commit(); //commit transaction

    }

}
