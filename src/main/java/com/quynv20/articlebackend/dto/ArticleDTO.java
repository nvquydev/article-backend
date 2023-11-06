package com.quynv20.articlebackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDTO {
    private String articleId;
    private String title;
    private Boolean isDiseaseArticle;
    private String description;
    private String content;
    private String introductionSection;
    private String symptomSection;
    private String reasonSection;
    private String riskSection;
    private String treatmentsSection;
    private String preventionSection;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date updatedAt;

    private CategoryDTO category;

    @JsonProperty("tags")
    private Set<TagDTO> tagDTOs;

    // Standard getters and setters
}
