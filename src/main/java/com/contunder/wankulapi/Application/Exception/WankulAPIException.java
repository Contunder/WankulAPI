package com.contunder.wankulapi.Application.Exception;

import org.springframework.http.HttpStatus;

public class WankulAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public WankulAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
