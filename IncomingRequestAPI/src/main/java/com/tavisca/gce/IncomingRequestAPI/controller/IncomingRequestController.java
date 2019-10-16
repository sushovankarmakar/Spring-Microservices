package com.tavisca.gce.IncomingRequestAPI.controller;

import com.tavisca.gce.IncomingRequestAPI.model.Footballer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IncomingRequestController {

    @PostMapping(path = "/send")
    public ResponseEntity<String> handleIncomingRequest(@RequestBody Footballer footballer){
        final String url = "http://localhost:9002/validator/validate";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, footballer, String.class);

        return stringResponseEntity;
    }
}
