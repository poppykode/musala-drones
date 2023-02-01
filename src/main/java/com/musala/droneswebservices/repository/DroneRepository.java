package com.musala.droneswebservices.repository;

import com.musala.droneswebservices.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone,Long> {
}
