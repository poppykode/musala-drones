package com.musala.droneswebservices.exception;

import org.springframework.http.HttpStatus;

public class DronesAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public DronesAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public DronesAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
