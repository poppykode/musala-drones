package com.musala.droneswebservices.services.impl;

import com.musala.droneswebservices.config.FileStorage;
import com.musala.droneswebservices.entity.Drone;
import com.musala.droneswebservices.entity.Medicine;
import com.musala.droneswebservices.exception.DronesAPIException;
import com.musala.droneswebservices.exception.ResourceNotFoundException;
import com.musala.droneswebservices.payload.MedicineDto;
import com.musala.droneswebservices.repository.DroneRepository;
import com.musala.droneswebservices.repository.MedicineRepository;
import com.musala.droneswebservices.services.MedicineService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class MedicineServiceImpl implements MedicineService {
    private final Path fileStorageLocation;
    private final DroneRepository droneRepository;
    private final MedicineRepository medicineRepository;

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
    public MedicineDto loadMedications(Long droneId,String name, String filePath, String code, float weight) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new ResourceNotFoundException("Drone","id",droneId));
        Medicine medicine = new Medicine();
        medicine.setDrone(drone);
        medicine.setImage(filePath);
        medicine.setName(name);
        medicine.setCode(code);
        medicine.setWeight(weight);
        return mapToDto( medicineRepository.save(medicine));
    }
    private MedicineDto mapToDto(Medicine medicine){
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setDroneId(medicine.getId());
        medicineDto.setImage(medicine.getImage());
        medicineDto.setName(medicine.getName());
        medicineDto.setCode(medicine.getCode());
        medicineDto.setWeight(medicine.getWeight());
        medicineDto.setId(medicine.getId());
        return medicineDto;
    }
}


