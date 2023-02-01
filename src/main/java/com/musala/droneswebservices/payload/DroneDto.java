package com.musala.droneswebservices.payload;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DroneDto {
    private Long id;
    @Size(max = 100,message = "serial number can not be longer than 100 characters")
    private String serialNumber;
    private float weight;
    private float capacity;
    private String model;
    private String state;
}

