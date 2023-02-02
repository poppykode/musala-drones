package com.musala.droneswebservices.services;

import com.musala.droneswebservices.payload.MedicineDto;
import com.musala.droneswebservices.payload.MedicineRequest;
import jakarta.validation.constraints.Pattern;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MedicineService {
    public String storeFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);

    public MedicineDto loadMedications(Long droneId, String filePath, MedicineRequest medicineRequest);

    public List<MedicineDto> findLoadedMedicationsByDroneId(Long droneId);
}
