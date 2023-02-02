package com.musala.droneswebservices.services.impl;

import com.musala.droneswebservices.payload.MedicineDto;
import com.musala.droneswebservices.services.MedicineService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class MedicineServiceImpl implements MedicineService {
    @Override
    public String storeFile(MultipartFile file) {
        return null;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        return null;
    }

    @Override
    public MedicineDto saveDocument(Long droneId, String name, String filePath, String code, String weight) {
        return null;
    }
}
