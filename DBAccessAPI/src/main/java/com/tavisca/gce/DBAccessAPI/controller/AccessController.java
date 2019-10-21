package com.tavisca.gce.DBAccessAPI.controller;

import com.google.gson.Gson;
import com.tavisca.gce.DBAccessAPI.model.Footballer;
import com.tavisca.gce.DBAccessAPI.model.Request;
import com.tavisca.gce.DBAccessAPI.repository.FootballerRepository;
import com.tavisca.gce.DBAccessAPI.repository.RequestRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/football")
public class AccessController {

    @Autowired
    FootballerRepository footballerRepository;
    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/footballer")
    public List<Footballer> getAllFootballer(HttpServletRequest request) {
        List<Footballer> listOfAllFootballer = footballerRepository.findAll();
        boolean isValid = (listOfAllFootballer.size() != 0);
        insertRequest(UUID.randomUUID().toString(), isValid, new Date(),
                request.getRequestURI(), "ui_api");
        return footballerRepository.findAll();
    }

    @GetMapping("/footballer/{fid}")
    public ResponseEntity getFootballerById(@PathVariable int fid, HttpServletRequest request){
        Optional<Footballer> footballer = footballerRepository.findById(fid);
        footballer.orElseThrow(()-> new RuntimeException("Footballer id is not found"));

        insertRequest(UUID.randomUUID().toString(), true, new Date(),
                request.getRequestURI(), "ui_api");

        return ResponseEntity.status(HttpStatus.OK).body(footballer);
    }

    @PostMapping("/saveFootballerDetails")
    public String saveToFootballerDatabase(@RequestBody String soccer, HttpServletRequest request){

        JSONObject footballerJsonObject = new JSONObject(soccer);
        String transactionId = footballerJsonObject.getString("tid");
        Gson gson = new Gson();
        Footballer footballer = gson.fromJson(footballerJsonObject.getString("footballerDetails"),
                Footballer.class);

        footballerRepository.save(footballer);

        if(!footballerRepository.findById(footballer.getFid()).isPresent())
            throw new RuntimeException("This footballer is failed to save in the database");

        insertRequest(transactionId, true, new Date(),
                request.getRequestURI(), "--");

        System.out.println("This footballer is added in the database");
        return "This footballer is added in the database";
    }

    private void insertRequest(String transactionId, boolean isValid,
                               Date timestamp, String serviceFrom, String serviceTo){

        requestRepository.save( new Request(transactionId, isValid, timestamp, serviceFrom, serviceTo));
    }
}
