package com.quynv20.articlebackend.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quynv20.articlebackend.dao.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
}
