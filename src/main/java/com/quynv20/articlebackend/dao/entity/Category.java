package com.quynv20.articlebackend.dao.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorys")
@Setter
@Getter
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String category_id;
    private String category_name;

    public Category() {
    }

	public Category(String category_name) {
		super();
		this.category_name = category_name;
	}
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + "]";
	}
    
    

}
