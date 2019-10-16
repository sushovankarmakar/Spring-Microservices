package com.tavisca.gce.DBValidatorAPI.repository;

import com.tavisca.gce.DBValidatorAPI.model.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, Integer> {
}
