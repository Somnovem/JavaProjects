package com.itstep.homework_8.repositories;

import com.itstep.homework_8.models.ShopModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopModel, Long> {
    List<ShopModel> findByNameContainingOrCategoryContainingOrAddressContaining(String name, String category, String address);
}
