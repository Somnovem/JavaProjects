package com.itstep.homework_8.controllers;

import com.itstep.homework_8.models.ShopModel;
import com.itstep.homework_8.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping
    public List<ShopModel> getAll() {
        return shopRepository.findAll();
    }

    @GetMapping("/{id}")
    public ShopModel getById(@PathVariable Long id) {
        return shopRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop not found with id: " + id)
                );
    }

    @PostMapping
    public ShopModel add(@RequestBody ShopModel shop) {
        return shopRepository.save(shop);
    }

    @PutMapping("/{id}")
    public ShopModel update(@PathVariable Long id, @RequestBody ShopModel shopDetails) {
        ShopModel shop = shopRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop not found with id: " + id)
                );

        shop.setName(shopDetails.getName());
        shop.setAddress(shopDetails.getAddress());
        shop.setPhone(shopDetails.getPhone());
        shop.setEmail(shopDetails.getEmail());
        shop.setWebsite(shopDetails.getWebsite());
        shop.setCategory(shopDetails.getCategory());
        shop.setDescription(shopDetails.getDescription());

        return shopRepository.save(shop);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        shopRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop not found with id: " + id)
        );
        shopRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<ShopModel> searchByQuery(@RequestParam String query) {
        return shopRepository.findByNameContainingOrCategoryContainingOrAddressContaining(query, query, query);
    }
}