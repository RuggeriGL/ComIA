package com.universidadeuropea.comia.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private final HttpStatus httpStatus;

    public AppException(String message, HttpStatus http) {
        super(message);
        this.httpStatus = http;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
