package com.quynv20.articlebackend.service;

import java.util.List;

import com.quynv20.articlebackend.dao.entity.Brand;

public interface BrandService {
	List<Brand> getAllBrands();
	Brand getBrandById(Long id);
	Brand createBrand(Brand brand);
	Brand updateBrand(Long id, Brand brand);
    void deleteBrand(Long id);
}
