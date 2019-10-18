package com.tavisca.gce.IncomingRequestAPI.exceptioncontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException (Exception exception){
        final URI uri = URI.create("http://localhost:9090/exception/exceptionFound");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(uri, exception, String.class);
    }
}
