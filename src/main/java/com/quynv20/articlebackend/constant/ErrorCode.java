package com.quynv20.articlebackend.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {
    INTERVAL_RETRICTED_ERROR(128036, "Reach API rate limit."),
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
