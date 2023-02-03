package com.musala.droneswebservices.services.impl;

import com.musala.droneswebservices.entity.Drone;
import com.musala.droneswebservices.entity.State;
import com.musala.droneswebservices.payload.DroneDto;
import com.musala.droneswebservices.repository.DroneRepository;
import com.musala.droneswebservices.services.DroneService;
import com.musala.droneswebservices.utils.AppConstants;
import com.musala.droneswebservices.utils.ObjectMappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public DroneDto registerDrone(DroneDto droneDto) {
        Drone saveDrone = droneRepository.save(ObjectMappers.mapToDrone(droneDto));
        return ObjectMappers.mapToDroneDto(saveDrone);
    }

    @Override
    public List<DroneDto> availableDrones() {
        // drone has to be idle and battery level above 25
        return droneRepository.findByState(State.IDLE)
                .stream()
                .map(ObjectMappers::mapToDroneDto)
                .filter(drone ->  drone.getCapacity() > AppConstants.LOW_BATTERY_LEVEL)
                .collect(Collectors.toList());
    }

    //   manual conversion

}
