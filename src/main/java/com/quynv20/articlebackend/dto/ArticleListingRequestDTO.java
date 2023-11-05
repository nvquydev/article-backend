package com.quynv20.articlebackend.dto;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArticleListingRequestDTO extends RequestPagingFilterDTO {
    private String memberNumber;
    private boolean forceRefresh;
    private String lastChecksum;
    private List<String> filterByStatus = new ArrayList<String>();

    private List<String> filterByCategories = new ArrayList<String>();

    public String cacheKey() {
        return memberNumber;
    }

    public String getLastChecksum() {
        if (StringUtils.isEmpty(lastChecksum))
            lastChecksum = "";
        return lastChecksum;
    }
}
