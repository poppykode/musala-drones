package com.musala.droneswebservices.controller;

import com.musala.droneswebservices.payload.BatteryLevelResponse;
import com.musala.droneswebservices.payload.DroneDto;
import com.musala.droneswebservices.services.DroneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/available-drones")
    public ResponseEntity<List<DroneDto>> availableDrones(){
    return ResponseEntity.ok(droneService.availableDrones());
    }
    @GetMapping("/check-battery-levels/{droneId}")
    public ResponseEntity<BatteryLevelResponse> checkDroneBatteryLevel(@PathVariable Long droneId){
        return ResponseEntity.ok(droneService.checkDroneBatteryLevel(droneId));
    }
}
