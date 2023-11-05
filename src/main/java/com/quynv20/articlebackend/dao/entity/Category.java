package com.quynv20.articlebackend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Article> articles;
    @OneToMany(mappedBy = "category")
    private List<Tag> tags;
    // Getter and setter methods
}

