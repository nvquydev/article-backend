package com.quynv20.articlebackend.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "brands")
@Setter
@Getter
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brand_id;
    private String brand_name;
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "brand_id")
    private List<Product> list_product;

    public Brand() {
    }

	public Brand(String brand_name, String country, List<Product> list_product) {
		super();
		this.brand_name = brand_name;
		this.country = country;
		this.list_product = list_product;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Brand [brand_name=" + brand_name + ", country=" + country + ", list_product=" + list_product + "]";
	}


}

