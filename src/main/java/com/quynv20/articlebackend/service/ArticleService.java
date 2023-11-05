package com.quynv20.articlebackend.service;

import com.quynv20.articlebackend.dao.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(String id);
    Article createArticle(Article article);
    Article updateArticle(String id, Article article);
    void deleteArticle(String id);
}
