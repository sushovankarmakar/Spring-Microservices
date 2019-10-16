package com.tavisca.gce.DBValidatorAPI.controller;

import com.tavisca.gce.DBValidatorAPI.model.Footballer;
import com.tavisca.gce.DBValidatorAPI.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validator")
public class ValidationController {

    @Autowired
    FootballerRepository footballerRepository;

    @PostMapping("/validate")
    public String addFootballer(@RequestBody Footballer footballer){
        footballerRepository.save(footballer);
        return "User Added";
    }

}
