package com.quynv20.articlebackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quynv20.articlebackend.dao.entity.Product;
import com.quynv20.articlebackend.dao.repository.ProductRepository;
import com.quynv20.articlebackend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		product = productRepository.findById(id).get();
		product.setProduct_name(product.getProduct_name());
		product.setType(product.getProduct_name());
		product.setQuantity(product.getQuantity());
		product.setImage(product.getImage());
		product.setPrice(product.getPrice());
		product.setDescription(product.getDescription());
		product.setCategory_id(product.getCategory_id());
		product.setBrand_id(product.getBrand_id());
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
