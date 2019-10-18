package com.tavisca.gce.IncomingRequestAPI.exceptioncontroller;

import com.tavisca.gce.IncomingRequestAPI.model.UserInput;
import com.tavisca.gce.IncomingRequestAPI.repository.UserInputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@ControllerAdvice
public class ExceptionController {

    @Autowired
    UserInputRepository repository;


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException (Exception exception, HttpServletRequest request){

        final URI uri = URI.create("http://localhost:9090/exception/exceptionFound");

        insertRequest(UUID.randomUUID().toString(), exception.getMessage(), false,
                new Date(), request.getRequestURI(), uri.getPath());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(uri, exception, String.class);
    }

    private void insertRequest(String transactionId, String footballer, boolean isValid,
                               Date timestamp, String serviceFrom, String serviceTo){

        repository.save( new UserInput(transactionId, footballer, isValid, timestamp, serviceFrom, serviceTo));
    }
}
