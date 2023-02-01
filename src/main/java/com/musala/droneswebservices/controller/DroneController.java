package com.musala.droneswebservices.controller;

import com.musala.droneswebservices.payload.DroneDto;
import com.musala.droneswebservices.services.DroneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public ResponseEntity<DroneDto> registerDrone(@Valid @RequestBody DroneDto droneDto){
      return new ResponseEntity<>(droneService.registerDrone(droneDto), HttpStatus.CREATED);
    }
}
