package com.vti.rw41;

import com.vti.rw41.entity.*;
import com.vti.rw41.enumurations.ProductStatus;
import com.vti.rw41.repository.CategoryRepository;
import com.vti.rw41.repository.ProductRepository;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Optional<CategoryEntity> category = CategoryRepository.findByName("Điện Tử");

        if (category.isPresent()) {

            CategoryEntity categoryEntity = category.get();

            System.out.println("category name = " + categoryEntity.getName());
            System.out.println("products: ");
            for (ProductEntity product : categoryEntity.getProducts()) {
                System.out.println(product.getId() + " -- " + product.getProductName());
            }

        }

//        List<ProductEntity> products = ProductRepository.findAllProduct();
//
//        for (ProductEntity product : products) {
//
//            if (category.isPresent()) {
//                product.setCategory(category.get());
//            }
//
//            ProductRepository.saveOrUpdate(product);
//            System.out.println(product);
//        }

//        List<ProductEntity> products = ProductRepository.findByName("San pham 4");
//
//        for (ProductEntity product : products) {
//            System.out.println(product.getCategory().getName());
//        }

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
