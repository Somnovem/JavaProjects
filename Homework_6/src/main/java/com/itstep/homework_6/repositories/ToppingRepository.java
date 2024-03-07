package com.itstep.homework_6.repositories;

import com.itstep.homework_6.models.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long>
{
}
