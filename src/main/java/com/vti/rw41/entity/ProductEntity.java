package com.vti.rw41.entity;

import com.vti.rw41.enumurations.ProductStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product") // -> SQL: select * from product
public class ProductEntity  extends BaseEntity { // -> from ProductEntity

    @Id
    @GeneratedValue(generator = "productIdSeq") // -> su sung seq
    @SequenceGenerator( // khai bao seq
            sequenceName = "productIdSeq", //-> ten cua sequence
            name = "productIdSeq",//-> ten cua sequence
            initialValue = 1, //gia tri dau tien
            allocationSize = 2) // buoc nhay
    private Integer id;

    @Column(name = "name")
    private String productName;

    private Double price;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    private CategoryEntity category;

}
