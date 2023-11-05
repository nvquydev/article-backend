package com.quynv20.articlebackend.service.impl;

import com.quynv20.articlebackend.dao.entity.Article;
import com.quynv20.articlebackend.dao.repository.ArticleRepository;
import com.quynv20.articlebackend.exception.ResourceNotFoundException;
import com.quynv20.articlebackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(String id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + id));
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(String id, Article updatedArticle) {
        Article article = getArticleById(id);
        article.setTitle(updatedArticle.getTitle());
        article.setDescription(updatedArticle.getDescription());
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(String id) {
        Article article = getArticleById(id);
        articleRepository.delete(article);
    }
}
