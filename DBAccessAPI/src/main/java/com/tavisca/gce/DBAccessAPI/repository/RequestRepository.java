package com.tavisca.gce.DBAccessAPI.repository;

import com.tavisca.gce.DBAccessAPI.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
