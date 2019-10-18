package com.tavisca.gce.ExceptionHandlerAPI.logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
@RequestMapping("/exception")
public class ExceptionLogger extends ResponseEntityExceptionHandler {

    @PostMapping("/exceptionFound")
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(@RequestBody Exception exception,
                                                                       WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
                request.getDescription(false));
        System.out.println(exception);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.ACCEPTED);
    }
}