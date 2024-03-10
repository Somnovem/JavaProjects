package com.itstep.homework_7.repositories;

import com.itstep.homework_7.models.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsModel, Long> {

}
