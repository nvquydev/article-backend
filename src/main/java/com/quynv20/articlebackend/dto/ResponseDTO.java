package com.quynv20.articlebackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quynv20.articlebackend.Utils.ValidateUtil;
import com.quynv20.articlebackend.constant.ErrorCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 1116091598299674086L;

    @JsonProperty(value = "errorCode")
    private Integer errorCode;

    @JsonProperty(value = "message")
    private String message;

    @JsonIgnore
    private HttpStatus httpStatus;

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        errorCode = errorCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        message = message;
    }

    /**
     * @return the httpStatus
     */
    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        httpStatus = httpStatus;
    }

    /**
     * Update errorCode and message based on errorCode obj
     *
     * @param errorCode
     */
    public void setError(ErrorCode errorCode) {
        if (errorCode != null) {
            this.errorCode = errorCode.getErrorCode();
            message = errorCode.getMessage();
        }
    }

    /**
     * Determine the response is error or not. The response is error if the errorId and message are
     * available
     *
     * @return
     */
    public boolean hasError() {
        return errorCode != null && errorCode > 0 && ValidateUtil.isNotNullOrEmpty(message);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResponseDTO [message=" + message + ", errorCode=" + errorCode + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        // Never use instanceof or it will break subclasses' equals method
        if (obj != null && obj.getClass() != getClass()) return false;

        ResponseDTO res = (ResponseDTO) obj;

        return Objects.equals(errorCode, res.errorCode)
                && Objects.equals(httpStatus, res.httpStatus)
                && Objects.equals(message, res.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, httpStatus, httpStatus);
    }
}
