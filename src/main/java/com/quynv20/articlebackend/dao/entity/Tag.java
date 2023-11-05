package com.quynv20.articlebackend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "Tag")
public class Tag {
    @Id
    @Column(name = "tag_id")
    private String tagId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();


}

