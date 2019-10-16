package com.tavisca.gce.DBAccessAPI.repository;

import com.tavisca.gce.DBAccessAPI.model.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, Integer> {
}
