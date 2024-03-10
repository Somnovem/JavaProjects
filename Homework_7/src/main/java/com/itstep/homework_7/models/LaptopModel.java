package com.itstep.homework_7.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "laptops")
@Data
@EqualsAndHashCode(callSuper = true)
public class LaptopModel extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
}