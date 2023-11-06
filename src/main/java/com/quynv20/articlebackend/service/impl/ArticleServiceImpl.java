package com.quynv20.articlebackend.service.impl;

import com.quynv20.articlebackend.Utils.PagingUtils;
import com.quynv20.articlebackend.constant.ArticleBackendConst;
import com.quynv20.articlebackend.constant.ErrorCode;
import com.quynv20.articlebackend.dao.entity.Article;
import com.quynv20.articlebackend.dao.repository.ArticleRepository;
import com.quynv20.articlebackend.dto.ArticleListingRequestDTO;
import com.quynv20.articlebackend.exception.BaseException;
import com.quynv20.articlebackend.exception.ResourceNotFoundException;
import com.quynv20.articlebackend.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles(ArticleListingRequestDTO requestDTO) throws BaseException {
        log.info("[getAllArticles] Start get all article from DB");
        List<Article> filtedArticles;
        Integer size = requestDTO.getPageSize();
        Integer page = requestDTO.getPage();
        if (requestDTO.getArticleType().equals(ArticleBackendConst.HEALTH_ARTICLE_TYPE)){
            filtedArticles = getAllHealthArticles(requestDTO);
        }else{
            filtedArticles = getAllDisieseArticles(requestDTO);
        }
        // paging campaigns
        if (filtedArticles.size() <= size) {
            return filtedArticles;
        }
        if (size <= 0) {
            log.error("Page Size is invalid: " + size);
            throw  new BaseException(ErrorCode.INVALID_PAGE_SIZE_ERROR, HttpStatus.BAD_REQUEST);
        }
        if (page <= 0) {
            log.error("Page Number is invalid: " + page);
            throw  new BaseException(ErrorCode.INVALID_PAGE_NUMBER_ERROR, HttpStatus.BAD_REQUEST);
        }
        filtedArticles = PagingUtils.applyPaging(page - 1, size, filtedArticles);
        log.info("[getAllArticles] End get all article from DB");
        return filtedArticles;
    }
    public List<Article> getAllHealthArticles(ArticleListingRequestDTO requestDTO) throws BaseException {
        log.info("[getAllHealthArticles] Start get health all article from DB");
        List<Article> filtedArticles = new ArrayList<>();
        try {
            List<Article> articleList = articleRepository.findAll();
            String status= requestDTO.getStatus();
            if (requestDTO.getArticleType().equals(ArticleBackendConst.HEALTH_ARTICLE_TYPE)){
                filtedArticles = articleList.stream()
                        .filter(e-> !e.getIsDiseaseArticle() && e.getStatus().equals(status))
                        .collect(Collectors.toList());
            }
        }catch (Exception e){
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("[getAllHealthArticles] End get all article from DB");
        return filtedArticles;
    }
    public List<Article> getAllDisieseArticles(ArticleListingRequestDTO requestDTO) throws BaseException {
        log.info("[getAllDisieseArticles] Start get health all article from DB");
        List<Article> filtedArticles = new ArrayList<>();
        try {
            List<Article> articleList = articleRepository.findAll();
            String status= requestDTO.getStatus();
            if (requestDTO.getArticleType().equals(ArticleBackendConst.HEALTH_ARTICLE_TYPE)){
                filtedArticles = articleList.stream()
                        .filter(e-> e.getIsDiseaseArticle() && e.getStatus().equals(status))
                        .collect(Collectors.toList());
            }
        }catch (Exception e){
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("[getAllDisieseArticles] End get all article from DB");
        return filtedArticles;

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
