package com.tavisca.gce.DBValidatorAPI.controller;

import com.google.gson.Gson;
import com.tavisca.gce.DBValidatorAPI.model.Footballer;
import com.tavisca.gce.DBValidatorAPI.model.ValidationCheck;
import com.tavisca.gce.DBValidatorAPI.repository.ValidationCheckRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/validator")
public class ValidationController {

    @Autowired
    ValidationCheckRepository repository;

    @PostMapping("/validate")
    public String validateFootballer(@RequestBody String soccer, HttpServletRequest request) throws Exception {

        System.out.println(soccer);

        JSONObject footballerJsonObject = new JSONObject(soccer);
        String transactionId = footballerJsonObject.getString("tid");

        Gson gson = new Gson();
        Footballer footballer = gson.fromJson(footballerJsonObject.getString("footballerDetails"),
                Footballer.class);
        System.out.println(footballer);

        if(footballer.getFid() < 0 )
            throw new Exception("Footballer id is not valid");
        else if (footballer.getName().length() <= 0 ||footballer.getName() == null )
            throw new Exception("There is no name");
        else if (footballer.getTeam().length() <= 0 ||footballer.getTeam() == null )
            throw new Exception("There is no team");
        else if (footballer.getUsername().length() <= 0 || footballer.getUsername() == null )
            throw new Exception("There is no username");
        else if (footballer.getPassword().length() < 8 || footballer.getPassword() == null )
            throw new Exception("Password length is less than 8 character");

        final URI uri = URI.create("http://localhost:8080/football/saveFootballerDetails");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(uri, footballerJsonObject.toString(),
                String.class);

        insertRequest(transactionId, soccer, true, new Date(),
                request.getRequestURI(), uri.getPath());

        return "This is a valid user";
    }

    private void insertRequest(String transactionId, String footballer, boolean isValid,
                               Date timestamp, String fromService, String toService){

        repository.save( new ValidationCheck(transactionId, footballer, isValid, timestamp, fromService, toService));
    }
}
