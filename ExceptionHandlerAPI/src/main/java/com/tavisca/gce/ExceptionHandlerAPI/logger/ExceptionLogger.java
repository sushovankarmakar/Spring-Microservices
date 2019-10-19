package com.tavisca.gce.ExceptionHandlerAPI.logger;

import com.tavisca.gce.ExceptionHandlerAPI.model.ExceptionResponse;
import com.tavisca.gce.ExceptionHandlerAPI.repository.ExceptionLoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
@RequestMapping("/exception")
public class ExceptionLogger extends ResponseEntityExceptionHandler {

    @Autowired
    ExceptionLoggerRepository repository;

    @PostMapping("/exceptionFound")
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(@RequestBody Exception exception,
                                                                       WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
                request.getDescription(false));
        repository.save(exceptionResponse);
        System.out.println(exception);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.ACCEPTED );
    }

    @GetMapping("/allExceptions")
    public List<ExceptionResponse> getAllExceptions(){
        return repository.findAll();
    }
}