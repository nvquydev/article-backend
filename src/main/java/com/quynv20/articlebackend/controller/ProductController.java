package com.quynv20.articlebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quynv20.articlebackend.Utils.DTOEntityConverter;
import com.quynv20.articlebackend.dao.entity.Product;
import com.quynv20.articlebackend.dto.ProductDTO;
import com.quynv20.articlebackend.service.ProductService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	@PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO ProductDTO) {
        Product Product = DTOEntityConverter.convertToEntity(ProductDTO, Product.class);
        Product createdProduct = productService.createProduct(Product);
        ProductDTO createdProductDTO = DTOEntityConverter.convertToDTO(createdProduct, ProductDTO.class);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product Product = productService.getProductById(id);
        ProductDTO ProductDTO = DTOEntityConverter.convertToDTO(Product, ProductDTO.class);
        return new ResponseEntity<>(ProductDTO, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO ProductDTO) {
        Product ProductToUpdate = DTOEntityConverter.convertToEntity(ProductDTO, Product.class);
        Product updatedProduct = productService.updateProduct(id, ProductToUpdate);
        ProductDTO updatedProductDTO = DTOEntityConverter.convertToDTO(updatedProduct, ProductDTO.class);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
