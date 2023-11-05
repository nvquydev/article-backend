package com.quynv20.articlebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RequestPagingFilterDTO implements Serializable {
    private static final long serialVersionUID = -2448904707330847157L;

    private String sortBy; //ExpiredDate or RecentlyAdded

    private String sortOrder; //desc/asc

    private Integer limitHistory;

    private Integer currentPage;

    private Integer pageSize;
}
