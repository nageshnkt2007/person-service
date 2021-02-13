package com.service.person.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String body){
        super(body);
    }

    public BadRequestException(String body, Throwable ex){
        super(body,ex);
    }
}
