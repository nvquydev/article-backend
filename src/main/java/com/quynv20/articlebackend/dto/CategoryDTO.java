package com.quynv20.articlebackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {
    private String categoryId;
    private String name;

    // To prevent circular reference, we can ignore articles and tags in the category DTO,
    // or handle it with @JsonManagedReference/@JsonBackReference if it's really needed
     @JsonProperty("articles")
     private List<ArticleDTO> articleDTOs;
     @JsonProperty("tags")
     private List<TagDTO> tagDTOs;

    // Standard getters and setters
}
