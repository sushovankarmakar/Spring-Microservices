package com.tavisca.gce.DBValidatorAPI.repository;

import com.tavisca.gce.DBValidatorAPI.model.ValidationCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationCheckRepository extends JpaRepository<ValidationCheck, Integer> {
}
