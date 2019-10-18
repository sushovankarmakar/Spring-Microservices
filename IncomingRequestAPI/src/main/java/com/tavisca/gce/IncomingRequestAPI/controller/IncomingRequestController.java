package com.tavisca.gce.IncomingRequestAPI.controller;

import com.tavisca.gce.IncomingRequestAPI.model.Footballer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/input")
public class IncomingRequestController {

    @PostMapping(path = "/send")
    public ResponseEntity<String> handleIncomingRequest(@RequestBody Footballer footballer){
        final URI uri = URI.create("http://localhost:9002/validator/validate");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(uri, footballer, String.class);
        //throw new RuntimeException("Exception while sending data");
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
