package com.quynv20.articlebackend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "articles")
@Setter
@Getter
public class Article {
    @Id
    @Column(name = "article_id")
    private String articleId;

    @Column(name = "title")
    private String title;

    @Column(name = "isDiseaseArticle")
    private Boolean isDiseaseArticle;
    @Column(name = "description")
    private String description;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "introduction_section", columnDefinition = "TEXT")
    private String introductionSection;
    @Column(name = "symptom_section", columnDefinition = "TEXT")
    private String symptomSection;
    @Column(name = "reason_section", columnDefinition = "TEXT")
    private String reasonSection;

    @Column(name = "risk_section", columnDefinition = "TEXT")
    private String riskSection ;

    @Column(name = "treatments_section", columnDefinition = "TEXT")
    private String treatmentsSection;
    @Column(name = "prevention_section", columnDefinition = "TEXT")
    private String preventionSection;
    @Column(name = "created_at")
        private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
    // Getter and setter methods
}

