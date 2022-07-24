package com.vti.rw41.entity.department;


import com.vti.rw41.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private Integer id;

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

}
