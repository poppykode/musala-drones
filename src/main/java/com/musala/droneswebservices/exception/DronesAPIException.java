package com.musala.droneswebservices.exception;

import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

public class DronesAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;
    public DronesAPIException(String message) {
        super(message);
    }
    public DronesAPIException(String message,Throwable ex) {
        super(message,ex);
    }
    public DronesAPIException(HttpStatus status, String message) {
        super(message);
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
