package com.vti.rw41.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentDto {
    private Integer id;
    private String name;
    private String address;
}
