package com.tavisca.gce.DBAccessAPI.controller;

import com.tavisca.gce.DBAccessAPI.model.Footballer;
import com.tavisca.gce.DBAccessAPI.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/football")
public class AccessController {

    @Autowired
    FootballerRepository footballerRepository;


    @GetMapping("/footballer")
    public List<Footballer> getAllFootballer() {
        return footballerRepository.findAll();
    }

    @GetMapping("/footballer/{fid}")
    public ResponseEntity getFootballerById(@PathVariable int fid){
        Optional<Footballer> footballer = footballerRepository.findById(fid);
        footballer.orElseThrow(()-> new RuntimeException("Footballer id is not found"));
        return ResponseEntity.status(HttpStatus.OK).body(footballer);
    }
}
