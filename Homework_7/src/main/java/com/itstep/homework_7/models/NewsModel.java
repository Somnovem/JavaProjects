package com.itstep.homework_7.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "news")
@Data
@EqualsAndHashCode(callSuper = true)
public class NewsModel extends BaseModel {
    private String title;
    private String description;
    private String content;
    private String imageUrl;
}
