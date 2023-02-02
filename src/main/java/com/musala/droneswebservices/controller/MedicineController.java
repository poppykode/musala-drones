package com.musala.droneswebservices.controller;

import com.musala.droneswebservices.payload.MedicineDto;
import com.musala.droneswebservices.services.MedicineService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RequestMapping("/api/v1/medicines")
@RestController
public class MedicineController {
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping("/{droneId}")
    public ResponseEntity<MedicineDto> uploadFile(@RequestParam("image") MultipartFile image, @RequestParam("name") String name, @PathVariable Long droneId) {
        String fileName = medicineService.storeFile(image);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/medicines")
                .path("/download-image/")
                .path(fileName)
                .toUriString();
        MedicineDto medicine = medicineService.loadMedications(droneId,name,fileDownloadUri,"code",5);
        return new ResponseEntity<>(medicine, HttpStatus.CREATED);
    }

    @GetMapping("/download-image/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = medicineService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.print("Could not determine file type.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
