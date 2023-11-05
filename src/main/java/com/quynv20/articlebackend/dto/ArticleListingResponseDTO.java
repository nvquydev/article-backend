package com.quynv20.articlebackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleListingResponseDTO extends ResponseDTO implements Serializable {
    @JsonProperty("articleDTOS")
    private List<ArticleDTO> articleDTOS;
    @JsonProperty("checkSum")
    private String checkSum;

    public List<ArticleDTO> getArticleDTOS() {
        return articleDTOS;
    }

    public void setArticleDTOS(List<ArticleDTO> articleDTOS) {
        this.articleDTOS = articleDTOS;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
