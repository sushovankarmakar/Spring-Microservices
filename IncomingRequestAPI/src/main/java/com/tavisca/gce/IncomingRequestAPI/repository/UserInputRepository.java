package com.tavisca.gce.IncomingRequestAPI.repository;

import com.tavisca.gce.IncomingRequestAPI.model.UserInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInputRepository extends JpaRepository<UserInput, Integer> {
}
