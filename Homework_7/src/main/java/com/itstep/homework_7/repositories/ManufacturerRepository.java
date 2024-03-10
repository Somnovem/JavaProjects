package com.itstep.homework_7.repositories;

import com.itstep.homework_7.models.ManufacturerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<ManufacturerModel, Long> {

}
