package com.apper.theblogservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ServiceError handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ServiceError(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceError handleIdDoesNotExistException(IdDoesNotExistException ex) {
        return new ServiceError(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceError handleBlogDoesNotExistException(BlogDoesNotExistException ex) {
        return new ServiceError(ex.getMessage());
    }
}
