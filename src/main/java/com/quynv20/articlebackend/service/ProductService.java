package com.quynv20.articlebackend.service;

import java.util.List;

import com.quynv20.articlebackend.dao.entity.Product;

public interface ProductService {
	List<Product> getAllProducts();
	Product getProductById(Long id);
	Product createProduct(Product product);
	Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
