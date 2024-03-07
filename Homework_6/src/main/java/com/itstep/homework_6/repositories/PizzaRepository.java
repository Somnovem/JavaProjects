package com.itstep.homework_6.repositories;

import com.itstep.homework_6.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>
{
    Pizza findByName(String name);
}
