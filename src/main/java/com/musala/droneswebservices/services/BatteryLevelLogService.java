package com.musala.droneswebservices.services;

import com.musala.droneswebservices.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BatteryLevelLogService {
    Logger logger = LoggerFactory.getLogger(BatteryLevelLogService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat();
    private final DroneRepository droneRepository;

    public BatteryLevelLogService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Scheduled(fixedRate = 100000)
    public void reportBatteryLevelsForEachDrone() {
        logger.trace("_______________________________________________");
        logger.trace("Log Time: {}", dateFormat.format(new Date()));
        logger.trace("___________________##########___________________");
        droneRepository.findAll()
                .stream()
                .forEach(drone ->  {
                    logger.trace("Drone Serial No: {}  Battery Level: {}",drone.getSerialNumber(),drone.getBatteryLevel());
                });
        logger.trace("_______________________________________________");



    }
}
