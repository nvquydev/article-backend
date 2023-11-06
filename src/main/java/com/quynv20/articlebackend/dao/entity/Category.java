package com.quynv20.articlebackend.dao.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Entity
@Table(name = "categorys")
@Setter
@Getter
public class Category implements Serializable {
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
