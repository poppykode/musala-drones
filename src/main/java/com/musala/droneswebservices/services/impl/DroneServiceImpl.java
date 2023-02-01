package com.musala.droneswebservices.services.impl;

import com.musala.droneswebservices.entity.Drone;
import com.musala.droneswebservices.payload.DroneDto;
import com.musala.droneswebservices.repository.DroneRepository;
import com.musala.droneswebservices.services.DroneService;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public DroneDto registerDrone(DroneDto droneDto) {
        Drone saveDrone = droneRepository.save(mapToDrone(droneDto));
        return mapToDroneDto(saveDrone);
    }
//   manual conversion
    public Drone mapToDrone(DroneDto droneDto){
        Drone drone = new Drone();
        drone.setId(droneDto.getId());
        drone.setSerialNumber(droneDto.getSerialNumber());
        drone.setCapacity(droneDto.getCapacity());
        drone.setState(droneDto.getState());
        drone.setModel(droneDto.getModel());
        drone.setWeight(droneDto.getWeight());
        return  drone;

    }

    public DroneDto mapToDroneDto(Drone drone){
        DroneDto droneDto = new DroneDto();
        droneDto.setId(drone.getId());
        droneDto.setSerialNumber(drone.getSerialNumber());
        droneDto.setCapacity(drone.getCapacity());
        droneDto.setState(drone.getState());
        droneDto.setModel(drone.getModel());
        droneDto.setWeight(drone.getWeight());
        return  droneDto;
    }
}
