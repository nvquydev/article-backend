package com.quynv20.articlebackend.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {
    INTERVAL_RETRICTED_ERROR(128036, "Reach API rate limit."),
    INVALID_PAGE_SIZE_ERROR(121009, "Page Size is invalid."),
    INVALID_PAGE_NUMBER_ERROR(121008, "Page Number is out of bound."),
    INTERNAL_SERVER_ERROR(128800, "Internal Server Error"),
    NO_DATA_CHANGED(128037, "No data changed.");


    /**
     * The field to get errorCode value from enum
     */

    private int errorCode;

    /**
     * The field to get message value from enum
     */
    private String message;

    /**
     * @param errorCode
     * @param message
     */
    private ErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
