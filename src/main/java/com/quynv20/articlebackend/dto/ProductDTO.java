package com.quynv20.articlebackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quynv20.articlebackend.dao.entity.Brand;
import com.quynv20.articlebackend.dao.entity.Category;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
	private Long product_id;
    private String product_name;
    private String type;
    private int quantity;
    private String image;
    private double price;
    private String description;

    private Category category_id;

    private Brand brand_id;
    
    // Standard getters and setters
}
