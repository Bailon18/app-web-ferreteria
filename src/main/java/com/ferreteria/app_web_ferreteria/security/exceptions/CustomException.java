package com.ferreteria.app_web_ferreteria.security.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    private HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
