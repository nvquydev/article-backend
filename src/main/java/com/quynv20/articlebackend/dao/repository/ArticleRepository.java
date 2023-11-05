package com.quynv20.articlebackend.dao.repository;

import com.quynv20.articlebackend.dao.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {


}
