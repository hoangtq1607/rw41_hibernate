package com.vti.rw41.entity.department;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class DetailDepartment implements Serializable {

    @Id
    @Column(name = "AddressID")
    private Integer addressId;

    @Id
    @Column(name = "DepartmentID")
    private Integer departmentID;

    private Integer EmulationPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentID", insertable = false, updatable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AddressID", insertable = false, updatable = false)
    private Address address;

}
