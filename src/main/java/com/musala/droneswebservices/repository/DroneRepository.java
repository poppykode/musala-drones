package com.musala.droneswebservices.repository;

import com.musala.droneswebservices.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone,Long> {
    Optional<Drone> findById(Long id);
}
