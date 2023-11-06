package com.quynv20.articlebackend.service;

import com.quynv20.articlebackend.dao.entity.Article;
import com.quynv20.articlebackend.dto.ArticleListingRequestDTO;
import com.quynv20.articlebackend.exception.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    List<Article> getAllArticles(ArticleListingRequestDTO requestDTO) throws BaseException;
    Article getArticleById(String id);
    Article createArticle(Article article);
    Article updateArticle(String id, Article article);
    void deleteArticle(String id);
}
