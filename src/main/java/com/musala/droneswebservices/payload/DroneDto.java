package com.musala.droneswebservices.payload;

import com.musala.droneswebservices.entity.Model;
import com.musala.droneswebservices.entity.State;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DroneDto {
    private Long id;
    @Size(min = 2, message = "serial number is too short requires a minimum of atleast 2 characters")
    @Size(max = 100, message = "serial number can not be longer than 100 characters}")
    private String serialNumber;
    private int batteryLevel;
    private Model model;
    private State state;
}

