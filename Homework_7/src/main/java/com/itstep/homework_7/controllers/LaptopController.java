package com.itstep.homework_7.controllers;


import com.itstep.homework_7.repositories.LaptopRepository;
import com.itstep.homework_7.models.LaptopModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laptop")
public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;

    @GetMapping("/all")
    public List<LaptopModel> getAllLaptopModels() {
        return laptopRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LaptopModel> getLaptopModelById(@PathVariable("id") Long id) {
        return laptopRepository.findById(id);
    }
}