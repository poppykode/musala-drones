package com.musala.droneswebservices.services;

import com.musala.droneswebservices.payload.MedicineDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MedicineService {
    public String storeFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);

    public MedicineDto saveDocument(Long droneId,  String name, String filePath,String code,String weight);
}
