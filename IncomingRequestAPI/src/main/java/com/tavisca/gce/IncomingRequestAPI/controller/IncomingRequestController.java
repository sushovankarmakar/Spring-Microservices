package com.tavisca.gce.IncomingRequestAPI.controller;

import com.tavisca.gce.IncomingRequestAPI.model.UserInput;
import com.tavisca.gce.IncomingRequestAPI.repository.UserInputRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/input")
public class IncomingRequestController {

    @Autowired
    UserInputRepository repository;

    @PostMapping(path = "/send")
    public ResponseEntity<String> handleIncomingRequest(@RequestBody String footballer, HttpServletRequest request){

        if(footballer == null) throw new RuntimeException("Nothing inside the football");
        else if(!footballer.contains("fid")) throw new RuntimeException("There is no fid field in football object");
        else if(!footballer.contains("name")) throw new RuntimeException("There is no name field in football object");
        else if(!footballer.contains("team")) throw new RuntimeException("There is no team field in football object");
        else if(!footballer.contains("username")) throw new RuntimeException("There is no username field in football object");
        else if(!footballer.contains("password")) throw new RuntimeException("There is no password field in football object");

        String transactionId = UUID.randomUUID().toString();
        JSONObject footballerJsonObject = new JSONObject();
        footballerJsonObject.put("tid", transactionId);
        footballerJsonObject.put("footballerDetails", footballer);

        System.out.println(footballerJsonObject);

        final URI uri = URI.create("http://localhost:9002/validator/validate");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(uri, footballerJsonObject.toString(),
                String.class);

        insertRequest(transactionId, footballer, true, new Date(),
                request.getRequestURI(), uri.getPath());
        return stringResponseEntity;
    }

    @PostMapping("/error")
    public ResponseEntity<String> handleError(Exception exception){
        System.out.println("Inside error");
        final URI uri = URI.create("http://localhost:9090/exception/exceptionFound");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(uri, exception, String.class);
    }

    private void insertRequest(String transactionId, String footballer, boolean isValid,
                               Date timestamp, String fromService, String toService){

        repository.save( new UserInput(transactionId, footballer, isValid, timestamp, fromService, toService));
    }
}
