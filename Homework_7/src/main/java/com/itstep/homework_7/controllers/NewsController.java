package com.itstep.homework_7.controllers;

import com.itstep.homework_7.models.NewsModel;
import com.itstep.homework_7.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/list")
    public List<NewsModel> getAllNews() {
        return newsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<NewsModel> getNewsById(@PathVariable("id") Long id) {
        return newsRepository.findById(id);
    }
}
