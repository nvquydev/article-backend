package com.quynv20.articlebackend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quynv20.articlebackend.dao.entity.Product;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandDTO {
	private Long brand_id;
    private String brand_name;
    private String country;
    
    @JsonProperty("products")
    private List<Product> list_product;

    // Standard getters and setters
}
