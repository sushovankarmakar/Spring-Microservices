package com.tavisca.gce.DBAccessAPI.controller;

import com.tavisca.gce.DBAccessAPI.model.Footballer;
import com.tavisca.gce.DBAccessAPI.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Optional<Footballer> getFootballerById(@PathVariable int fid){
        return footballerRepository.findById(fid);
    }
}
