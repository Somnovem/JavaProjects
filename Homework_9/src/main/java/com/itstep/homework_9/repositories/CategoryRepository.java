package com.itstep.homework_9.repositories;

import com.itstep.homework_9.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
