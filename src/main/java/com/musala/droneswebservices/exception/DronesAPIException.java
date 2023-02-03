package com.musala.droneswebservices.exception;

import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

public class DronesAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;
    private Throwable ex;
    public DronesAPIException(String message) {
        this.message = message;
    }
    public DronesAPIException(String message,Throwable ex) {
        this.message = message;
        this.ex = ex;
    }
    public DronesAPIException(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
