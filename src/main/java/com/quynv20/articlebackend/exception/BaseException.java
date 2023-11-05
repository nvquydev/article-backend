package com.quynv20.articlebackend.exception;

import com.quynv20.articlebackend.constant.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseException extends Exception {
    private static final long serialVersionUID = 6959388629961794442L;
    /**
     * The Message.
     */
    protected final String message;
    /**
     * The Status code.
     */
    protected final Integer statusCode;
    /**
     * The Http status.
     */
    protected final HttpStatus httpStatus;
    /**
     * The Args.
     */
    protected final Object[] args = null;

    /**
     * Instantiates a new Base exception.
     *
     * @param statusCode the status code
     * @param message    the message
     * @param httpStatus the http status
     */
    public BaseException(Integer statusCode, String message, HttpStatus httpStatus) {
        super();
        this.message = message;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
    }

    public BaseException(ErrorCode errorCode, HttpStatus httpStatus) {
        super();
        this.statusCode = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
        this.httpStatus = httpStatus;
    }
}
