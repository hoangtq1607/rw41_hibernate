package com.vti.rw41.repository;

import com.vti.rw41.entity.dto.DepartmentDto;
import com.vti.rw41.entity.department.Department;
import com.vti.rw41.entity.department.DetailDepartment;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartmentRepository {

    public static void createDepartments() {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();

        for (int i = 0; i < 10; i++) {
            Department department = new Department();
            department.setDepartmentName("department_" + (i + 1));
            session.save(department);
        }
        session.getTransaction().commit();
    }

    public static List<DepartmentDto> getAllDepartments(String search, int page, int pageSize) {

        Session session = HibernateUtils.getSession();

        Query<DepartmentDto> query = session.createQuery("" +
                "select " +
                "new com.vti.rw41.entity.dto.DepartmentDto(d.id, d.departmentName, a.name) " +
                "from DetailDepartment dd " +
                " right join dd.department d left join dd.address a " +
                " WHERE d.departmentName like :departmentName " +
                "order by d.departmentName ", DepartmentDto.class)
                .setParameter("departmentName", "%" + search + "%")
                .setMaxResults(pageSize) // lay toi da pageSize phan tu
                .setFirstResult((page - 1) * pageSize); //bo qua N phan tu -- N = (page - 1) * pageSize

        return query.getResultList();
    }

    public static Optional<DepartmentDto> getDepartmentById(Integer id) {

        Session session = HibernateUtils.getSession();

        Query<DepartmentDto> query = session.createQuery("" +
                "select " +
                "new com.vti.rw41.entity.dto.DepartmentDto(d.id, d.departmentName, a.name) " +
                "from DetailDepartment dd " +
                " right join dd.department d left join dd.address a " +
                " WHERE d.id = :departmentId ", DepartmentDto.class)
                .setParameter("departmentId", id);

        return query.getResultStream()
                .findFirst();
    }

    public static Optional<DepartmentDto> getDepartmentByName(String name) {

        Session session = HibernateUtils.getSession();

        Query<DepartmentDto> query = session.createQuery("" +
                "select " +
                "new com.vti.rw41.entity.dto.DepartmentDto(d.id, d.departmentName, a.name) " +
                "from DetailDepartment dd " +
                " right join dd.department d left join dd.address a " +
                " WHERE d.departmentName = :departmentName ", DepartmentDto.class)
                .setParameter("departmentName", name);

        return query.getResultStream()
                .findFirst();
    }

    public static int getTotal(String search) {

        Session session = HibernateUtils.getSession();

        Query query = session.createQuery("" +
                "select count(d) " +
                "from Department d " +
                " WHERE d.departmentName like :departmentName")
                .setParameter("departmentName", "%" + search + "%");

        return ((Number) query.getSingleResult()).intValue();
    }

    public static List<DepartmentDto> getAllDepts(String search, int page, int pageSize) {

        Session session = HibernateUtils.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<DetailDepartment> query = cb.createQuery(DetailDepartment.class);
        Root<DetailDepartment> root = query.from(DetailDepartment.class);
        query.select(root)
                .where(cb.like(root.get("department")
                        .get("departmentName"), "%" + search + "%"));

        return session.createQuery(query)
                .setMaxResults(pageSize)
                .setFirstResult((page - 1) * pageSize)
                .stream()
                .map(dd -> new DepartmentDto(
                        dd.getDepartmentID(),
                        dd.getDepartment().getDepartmentName(),
                        dd.getAddress().getName()))
                .collect(Collectors.toList());

    }

}
