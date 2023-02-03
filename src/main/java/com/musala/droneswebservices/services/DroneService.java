package com.musala.droneswebservices.services;

import com.musala.droneswebservices.entity.Drone;
import com.musala.droneswebservices.payload.DroneDto;

import java.util.List;

public interface DroneService {

    DroneDto registerDrone(DroneDto droneDto);
    List<DroneDto> availableDrones();
    int checkDroneBatteryLevel(Long droneId);

}
