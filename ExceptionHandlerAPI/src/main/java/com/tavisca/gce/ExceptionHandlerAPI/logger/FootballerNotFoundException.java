package com.tavisca.gce.ExceptionHandlerAPI.logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FootballerNotFoundException extends RuntimeException {

    public FootballerNotFoundException(String exception){
        super(exception);
        System.out.println(exception);
    }
}