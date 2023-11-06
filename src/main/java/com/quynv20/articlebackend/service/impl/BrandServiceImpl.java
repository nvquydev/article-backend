package com.quynv20.articlebackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quynv20.articlebackend.dao.entity.Brand;
import com.quynv20.articlebackend.dao.repository.BrandRepository;
import com.quynv20.articlebackend.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	BrandRepository brandRepository;
	
	@Override
	public List<Brand> getAllBrands() {
		return brandRepository.findAll();
	}

	@Override
	public Brand getBrandById(Long id) {
		return brandRepository.findById(id).get();
	}

	@Override
	public Brand createBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand updateBrand(Long id, Brand brand) {
		brand = brandRepository.findById(id).get();
		brand.setBrand_name(brand.getBrand_name());
		brand.setCountry(brand.getCountry());
		return brandRepository.save(brand);
	}

	@Override
	public void deleteBrand(Long id) {
		brandRepository.deleteById(id);
	}

}
