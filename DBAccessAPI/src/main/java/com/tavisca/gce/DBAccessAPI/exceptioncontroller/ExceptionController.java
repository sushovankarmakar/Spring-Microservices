package com.tavisca.gce.DBAccessAPI.exceptioncontroller;

import com.tavisca.gce.DBAccessAPI.model.Request;
import com.tavisca.gce.DBAccessAPI.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Autowired
    RequestRepository requestRepository;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions (Exception exception, HttpServletRequest request){
        /*ExceptionResponse exceptionResponse = new ExceptionResponse( new Date(), exception.getMessage(),
                webRequest.getDescription(false));*/
        final URI uri = URI.create("http://localhost:9090/exception/exceptionFound");

        insertRequest(UUID.randomUUID().toString(), false, new Date(),
                request.getRequestURI(), uri.getPath());

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(uri, exception, String.class);
    }

    private void insertRequest(String transactionId, boolean isValid,
                               Date timestamp, String serviceFrom, String serviceTo){

        requestRepository.save( new Request(transactionId, isValid, timestamp, serviceFrom, serviceTo));
    }
}
