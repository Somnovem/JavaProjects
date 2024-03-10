package com.itstep.homework_7.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "manufacturers")
@Data
@EqualsAndHashCode(callSuper = true)
public class ManufacturerModel extends BaseModel {
    private String name;
    private String headquarters;
    private String logoUrl;
    private int employeesCount;
    private String info;
}
