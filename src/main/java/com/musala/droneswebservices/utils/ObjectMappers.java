package com.musala.droneswebservices.utils;

import com.musala.droneswebservices.entity.Drone;
import com.musala.droneswebservices.entity.Medicine;
import com.musala.droneswebservices.payload.DroneDto;
import com.musala.droneswebservices.payload.MedicineDto;

public class ObjectMappers {
    public static Drone mapToDrone(DroneDto droneDto){
        Drone drone = new Drone();
        drone.setId(droneDto.getId());
        drone.setSerialNumber(droneDto.getSerialNumber());
        drone.setCapacity(droneDto.getCapacity());
        drone.setState(droneDto.getState());
        drone.setModel(droneDto.getModel());
        return  drone;

    }

    public static DroneDto mapToDroneDto(Drone drone){
        DroneDto droneDto = new DroneDto();
        droneDto.setId(drone.getId());
        droneDto.setSerialNumber(drone.getSerialNumber());
        droneDto.setCapacity(drone.getCapacity());
        droneDto.setState(drone.getState());
        droneDto.setModel(drone.getModel());
        return  droneDto;
    }

    public static MedicineDto mapToDto(Medicine medicine){
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setDroneId(medicine.getDrone().getId());
        medicineDto.setImage(medicine.getImage());
        medicineDto.setName(medicine.getName());
        medicineDto.setCode(medicine.getCode());
        medicineDto.setWeight(medicine.getWeight());
        medicineDto.setId(medicine.getId());
        return medicineDto;
    }
}
