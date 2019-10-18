package com.tavisca.gce.DBValidatorAPI.controller;

import com.google.gson.Gson;
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
    public String addFootballer(@RequestBody String soccer) throws Exception {

        System.out.println(soccer);
        Gson gson = new Gson();
        Footballer footballer = gson.fromJson(soccer, Footballer.class);
        System.out.println(footballer);

        if(footballer.getFid() < 0 ) {
            throw new Exception("Footballer id is not valid");
        }
        else if (footballer.getName().length() <= 0 ||footballer.getName() == null ){
            throw new Exception("There is no name");
        }
        else if (footballer.getTeam().length() <= 0 ||footballer.getTeam() == null ){
            throw new Exception("There is no team");
        }
        else if (footballer.getUsername().length() <= 0 || footballer.getUsername() == null ){
            throw new Exception("There is no username");
        }
        else if (footballer.getPassword().length() < 8 || footballer.getPassword() == null ){
            throw new Exception("Password length is less than 8 character");
        }

        footballerRepository.save(footballer);
        return "User Added";
    }

}
