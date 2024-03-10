package com.itstep.homework_7.controllers;

import com.itstep.homework_7.repositories.ManufacturerRepository;
import com.itstep.homework_7.models.ManufacturerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("/list")
    public List<ManufacturerModel> getManufacturerInfo() {
        return manufacturerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ManufacturerModel> getManufacturerById(@PathVariable("id") Long id) {
        return manufacturerRepository.findById(id);
    }
}
