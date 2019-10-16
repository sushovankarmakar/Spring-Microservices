package com.tavisca.gce.DBAccessAPI.controller;

import com.tavisca.gce.DBAccessAPI.model.Footballer;
import com.tavisca.gce.DBAccessAPI.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/football")
public class AccessController {

    @Autowired
    FootballerRepository footballerRepository;

    @GetMapping("/footballer")
    public List<Footballer> getFootballer() {
        return footballerRepository.findAll();
    }
}
