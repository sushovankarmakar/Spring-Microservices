package com.tavisca.gce.DBAccessAPI.exceptioncontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Date;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleExceptions (Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse( new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        final URI uri = URI.create("http://localhost:9090/exception/exceptionFound");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(uri, exception, ExceptionResponse.class);
    }
}
