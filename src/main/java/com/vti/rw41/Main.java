package com.vti.rw41;

import com.vti.rw41.entity.ProductEntity;
import com.vti.rw41.entity.dto.DepartmentDto;
import com.vti.rw41.entity.dto.ProductDto;
import com.vti.rw41.entity.department.Account;
import com.vti.rw41.enumurations.ProductStatus;
import com.vti.rw41.repository.DepartmentRepository;
import com.vti.rw41.repository.ProductRepository;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

//        String search = "e";
////        List<DepartmentDto> allDepartments =
////                DepartmentRepository.getAllDepartments(search, 1, 5);
////        System.out.println("total = " + DepartmentRepository.getTotal(search));
////
////        for (DepartmentDto departmentDto : allDepartments) {
////            System.out.println(departmentDto);
////        }
////        // [1] [2] ... [6] [7]
////        // [1] [2] ... [14] [15]
////        // [1]
        Optional<DepartmentDto> departmentById = DepartmentRepository.getDepartmentById(1000);
        System.out.println(departmentById);
        Optional<DepartmentDto> departmentByName = DepartmentRepository.getDepartmentByName("Upton LLC");
        System.out.println(departmentByName);

    }

    private static void demoSQL() {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        NativeQuery<ProductEntity> nativeQuery = session.createNativeQuery("select * from product ", ProductEntity.class);

        List<ProductEntity> resultList = nativeQuery.getResultList();
        for (ProductEntity productEntity : resultList) {
            System.out.println(productEntity.getProductName() + " - " + productEntity.getPrice());
        }
    }

    private static void demoSelectDto() {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        //DTO -> data transfer object

        // select * from product where name like '%test%'
        Query<ProductDto> query = session.createQuery("" +
                "Select new com.vti.rw41.ProductDto(p.productName, p.price, c.name)" +
                " from ProductEntity p " +
                " left join p.category c ", ProductDto.class);

        List<ProductDto> resultList = query.getResultList();

        for (ProductDto dto : resultList) {

            System.out.println(dto);
        }
    }

    private static void demoUpdateHql() {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();
        // select * from product where name like '%test%'
        Query query = session.createQuery("" +
                "update ProductEntity p " +
                " set p.productName = :productName " +
                " WHERE p.id = :productId");

        query.setParameter("productId", 5);
        query.setParameter("productName", "updated product");

        query.executeUpdate();

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
