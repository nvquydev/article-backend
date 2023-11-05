package com.quynv20.articlebackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quynv20.articlebackend.configuration.AppConfig;
import com.quynv20.articlebackend.constant.ArticleBackendConst;
import com.quynv20.articlebackend.constant.ErrorCode;
import com.quynv20.articlebackend.dao.entity.Article;
import com.quynv20.articlebackend.dto.ArticleDTO;
import com.quynv20.articlebackend.dto.ArticleListingRequestDTO;
import com.quynv20.articlebackend.dto.ArticleListingResponseDTO;
import com.quynv20.articlebackend.exception.BaseException;
import com.quynv20.articlebackend.exception.ResourceNotFoundException;
import com.quynv20.articlebackend.service.RedisCacheService;
import com.quynv20.articlebackend.service.impl.ArticleServiceImpl;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private AppConfig appConfig;

    @PostMapping(value = ArticleBackendConst.API_GET_ARTICLE)
    public ArticleListingResponseDTO getAllArticles(@RequestBody ArticleListingRequestDTO requestDTO) throws JsonProcessingException, BaseException {
        log.info("[getAllArticles] End process getAllArticles");
        ArticleListingResponseDTO responseDTO = new ArticleListingResponseDTO();
        List<ArticleDTO> articleDTOs;
        String pullRefreshKey = "ArticleListingForceRequest" + requestDTO.cacheKey();
//        if (requestDTO.isForceRefresh()
//                && (!StringUtils.isEmpty(redisCacheService.getStringValueForKey(pullRefreshKey)))) {
//            throw new BaseException(ErrorCode.INTERVAL_RETRICTED_ERROR.getErrorCode(), ErrorCode.INTERVAL_RETRICTED_ERROR.getMessage(),
//                    HttpStatus.BAD_REQUEST);
//        }
        try {
            List<Article> articleList = articleService.getAllArticles();
            articleDTOs = articleList.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(ErrorCode.INTERVAL_RETRICTED_ERROR.getMessage());
            return responseDTO;
        }
        String jsonData = objectMapper.writeValueAsString(articleDTOs);
        String checksum = DigestUtils.md5Hex(jsonData);
        if (requestDTO.getLastChecksum().equalsIgnoreCase(checksum)) {
            responseDTO.setHttpStatus(HttpStatus.NOT_MODIFIED);
            responseDTO.setMessage(ErrorCode.NO_DATA_CHANGED.getMessage());
            responseDTO.setErrorCode(ErrorCode.NO_DATA_CHANGED.getErrorCode());
            return responseDTO;
        }
//        if (requestDTO.isForceRefresh())
//            redisCacheService.setStringValue(pullRefreshKey, "1", appConfig.getPullToRefreshTimeout());
        responseDTO.setHttpStatus(HttpStatus.OK);
        responseDTO.setArticleDTOS(articleDTOs);
        log.info("[getAllArticles] End process getAllArticles");
        return responseDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable String id) {

        log.info("[getArticleById] Start process getArticleById for ID: {}", id);
        try {
            Article article = articleService.getArticleById(id);
            ArticleDTO articleDTO = convertToDTO(article);
            return new ResponseEntity<>(articleDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error("[getArticleById] Article not found for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("[getArticleById] Internal server error for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } finally {
            log.info("[getArticleById] End process getArticleById for ID: {}", id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        log.info("[createArticle] Start process createArticle");
        try {
            Article articleToUpdate = convertToEntity(articleDTO);
            articleService.createArticle(articleToUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(ArticleBackendConst.CREATE_ARTICLE_SUCCESSFUL);
        } catch (Exception e) {
            log.error("[createArticle] Error creating article", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } finally {
            log.info("[createArticle] End process createArticle");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable String id, @Valid @RequestBody ArticleDTO articleDTO) {
        log.info("[updateArticle] Start process updateArticle for ID: {}", id);
        try {
            Article articleToUpdate = convertToEntity(articleDTO);
            articleService.updateArticle(id, articleToUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(ArticleBackendConst.UPDATE_ARTICLE_SUCCESSFUL);
        } catch (ResourceNotFoundException e) {
            log.error("[updateArticle] Article not found for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("[updateArticle] Error updating article for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } finally {
            log.info("[updateArticle] End process updateArticle for ID: {}", id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id) {
        log.info("[deleteArticle] Start process deleteArticle for ID: {}", id);
        try {
            articleService.deleteArticle(id);
            return ResponseEntity.status(HttpStatus.OK).body(ArticleBackendConst.DELETE_ARTICLE_SUCCESSFUL);
        } catch (ResourceNotFoundException e) {
            log.error("[deleteArticle] Article not found for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("[deleteArticle] Error deleting article for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } finally {
            log.info("[deleteArticle] End process deleteArticle for ID: {}", id);
        }
    }
    // Utility method to convert entity to DTO
    private ArticleDTO convertToDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }

    // Utility method to convert DTO to entity
    private Article convertToEntity(ArticleDTO dto) {
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        return article;
    }
}
