package com.vti.rw41.entity.department;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressID")
    private Integer id;

    @Column(name = "AddressName")
    private String name;
}
