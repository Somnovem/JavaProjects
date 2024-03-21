package com.itstep.homework_9.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "cuisines")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cuisine extends BaseModel {
    private String name;

    @OneToMany(mappedBy = "cuisine")
    private List<Recipe> recipes;
}
