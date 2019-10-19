package com.tavisca.gce.DBValidatorAPI.exceptioncontroller;

import com.tavisca.gce.DBValidatorAPI.model.ValidationCheck;
import com.tavisca.gce.DBValidatorAPI.repository.ValidationCheckRepository;
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
    ValidationCheckRepository repository;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions (Exception exception, HttpServletRequest request){
        final URI uri = URI.create("http://localhost:9090/exception/exceptionFound");

        insertRequest(UUID.randomUUID().toString(), exception.getMessage(), false,
                new Date(), request.getRequestURI(), uri.getPath());

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(uri, exception, String.class);
    }

    private void insertRequest(String transactionId, String footballer, boolean isValid,
                               Date timestamp, String fromService, String toService){

        repository.save( new ValidationCheck(transactionId, footballer, isValid, timestamp, fromService, toService));
    }
}
