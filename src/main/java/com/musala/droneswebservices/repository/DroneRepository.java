package com.musala.droneswebservices.repository;

import com.musala.droneswebservices.entity.Drone;
import com.musala.droneswebservices.entity.State;
import com.musala.droneswebservices.payload.DroneDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone,Long> {
    Optional<Drone> findById(Long id);
    List<Drone> findByState(State state);
}
