package com.tavisca.gce.IncomingRequestAPI.controller;

import com.tavisca.gce.IncomingRequestAPI.model.UserInput;
import com.tavisca.gce.IncomingRequestAPI.repository.UserInputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/input")
public class IncomingRequestController {

    @Autowired
    UserInputRepository repository;

    @PostMapping(path = "/send")
    public ResponseEntity<String> handleIncomingRequest(@RequestBody String footballer){
        final URI uri = URI.create("http://localhost:9002/validator/validate");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(uri, footballer, String.class);
        //throw new RuntimeException("Exception while sending data");
        UserInput userInput = new UserInput(footballer, new Date(),
                "IncomingRequestAPI", "DBValidatorAPI");
        repository.save(userInput);
        return stringResponseEntity;
    }

    @PostMapping("/error")
    public ResponseEntity<String> handleError(Exception exception){
        System.out.println("Inside error");
        final URI uri = URI.create("http://localhost:9090/exception/exceptionFound");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(uri, exception, String.class);
    }
}
