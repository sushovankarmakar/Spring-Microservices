package com.tavisca.gce.ExceptionHandlerAPI.repository;

import com.tavisca.gce.ExceptionHandlerAPI.model.ExceptionResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionLoggerRepository extends MongoRepository<ExceptionResponse, Integer> {
}