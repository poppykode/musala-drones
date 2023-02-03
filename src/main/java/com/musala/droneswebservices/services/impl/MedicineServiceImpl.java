package com.musala.droneswebservices.services.impl;

import com.musala.droneswebservices.config.FileStorage;
import com.musala.droneswebservices.entity.Drone;
import com.musala.droneswebservices.entity.Medicine;
import com.musala.droneswebservices.exception.DronesAPIException;
import com.musala.droneswebservices.exception.ResourceNotFoundException;
import com.musala.droneswebservices.payload.MedicineDto;
import com.musala.droneswebservices.payload.MedicineRequest;
import com.musala.droneswebservices.repository.DroneRepository;
import com.musala.droneswebservices.repository.MedicineRepository;
import com.musala.droneswebservices.services.DroneService;
import com.musala.droneswebservices.services.MedicineService;
import com.musala.droneswebservices.utils.AppConstants;
import com.musala.droneswebservices.utils.ObjectMappers;
import com.musala.droneswebservices.utils.Validators;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {
    Logger logger = LoggerFactory.getLogger(MedicineServiceImpl.class);
    private final Path fileStorageLocation;
    private final DroneRepository droneRepository;

    private final MedicineRepository medicineRepository;

    @Autowired
    private  DroneService droneService;
    public MedicineServiceImpl(FileStorage fileStorage,DroneRepository droneRepository,MedicineRepository medicineRepository ) {
        this.fileStorageLocation = Paths.get(fileStorage.getUploadDir()).toAbsolutePath().normalize();
        this.droneRepository = droneRepository;
        this.medicineRepository = medicineRepository;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new DronesAPIException(
                    "Unable to create the directory where the uploaded files will be stored.", ex);

        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String partFileName = RandomStringUtils.randomAlphanumeric(8);
        String fullFileName = partFileName + "-" + fileName;

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new DronesAPIException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fullFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fullFileName;
        } catch (IOException ex) {
            throw new DronesAPIException("Could not store file " + fullFileName + ". Please try again!", ex);
        }

    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new DronesAPIException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new DronesAPIException("File not found " + fileName, ex);
        }
    }

    @Override
    public MedicineDto loadMedications(Long droneId, String filePath, MedicineRequest medicineRequest) {
        // the drone has to be available plus within weight limit to be loaded with medications
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new ResourceNotFoundException("Drone","id",droneId));
        checkWeightLimitAndAvailability(drone,medicineRequest.getWeight());
        Medicine medicine = new Medicine();
        medicine.setDrone(drone);
        medicine.setImage(filePath);
        medicine.setName(Validators.validateName(medicineRequest.getName()));
        medicine.setCode(Validators.validateCode( medicineRequest.getCode()));
        medicine.setWeight(medicineRequest.getWeight());
        return ObjectMappers.mapToDto( medicineRepository.save(medicine));
    }

    private boolean checkWeightLimitAndAvailability(Drone drone,float medicineWeight){
        // check if drone is available for loading
        boolean isDroneAvailable = droneService.availableDrones().contains(ObjectMappers.mapToDroneDto(drone));
        logger.info("Drone availability: " + isDroneAvailable);
        if (!isDroneAvailable){
         throw new DronesAPIException("Drone is not available for loading");
        }
        // find current drone weight load
        float currentDroneWeight = findLoadedMedicationsByDroneId(drone.getId())
                .stream()
                .map(droneItem -> droneItem.getWeight())
                .reduce(medicineWeight,(total, droneItem) -> total + droneItem);
        logger.info("Current drone weight: " + currentDroneWeight);
        if(currentDroneWeight > AppConstants.WEIGHT_LIMIT_IN_GRAMS){
           throw new DronesAPIException("Drone has exceeded drone weight limit by " +  ( currentDroneWeight - AppConstants.WEIGHT_LIMIT_IN_GRAMS));
        }
        return true;
    } 


    @Override
    public List<MedicineDto> findLoadedMedicationsByDroneId(Long droneId) {
        List<Medicine> drone = medicineRepository.findByDroneId(droneId);
        return drone.stream().map(ObjectMappers::mapToDto).collect(Collectors.toList());
    }


}


