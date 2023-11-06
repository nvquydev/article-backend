package com.quynv20.articlebackend.dao.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Setter
@Getter
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String product_name;
    private String type;
    private int quantity;
    private String image;
    private double price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category_id;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable=false)
    private Brand brand_id;
    

    public Product() {
    }

	public Product(String product_name, String type, int quantity, String image, double price, String description,
			Category category_id, Brand brand_id) {
		super();
		this.product_name = product_name;
		this.type = type;
		this.quantity = quantity;
		this.image = image;
		this.price = price;
		this.description = description;
		this.category_id = category_id;
		this.brand_id = brand_id;
	}


	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", type=" + type + ", quantity="
				+ quantity + ", image=" + image + ", price=" + price + ", description=" + description + ", category_id="
				+ category_id + ", brand_id=" + brand_id + "]";
	}
}
