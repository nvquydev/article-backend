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
import com.quynv20.articlebackend.dao.entity.Brand;
import com.quynv20.articlebackend.dto.BrandDTO;
import com.quynv20.articlebackend.service.BrandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	@Autowired
    private BrandService BrandService;
	
	@PostMapping("/brands")
    public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody BrandDTO BrandDTO) {
        Brand Brand = DTOEntityConverter.convertToEntity(BrandDTO, Brand.class);
        Brand createdBrand = BrandService.createBrand(Brand);
        BrandDTO createdBrandDTO = DTOEntityConverter.convertToDTO(createdBrand, BrandDTO.class);
        return new ResponseEntity<>(createdBrandDTO, HttpStatus.CREATED);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        Brand Brand = BrandService.getBrandById(id);
        BrandDTO BrandDTO = DTOEntityConverter.convertToDTO(Brand, BrandDTO.class);
        return new ResponseEntity<>(BrandDTO, HttpStatus.OK);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandDTO BrandDTO) {
        Brand BrandToUpdate = DTOEntityConverter.convertToEntity(BrandDTO, Brand.class);
        Brand updatedBrand = BrandService.updateBrand(id, BrandToUpdate);
        BrandDTO updatedBrandDTO = DTOEntityConverter.convertToDTO(updatedBrand, BrandDTO.class);
        return new ResponseEntity<>(updatedBrandDTO, HttpStatus.OK);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        BrandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
