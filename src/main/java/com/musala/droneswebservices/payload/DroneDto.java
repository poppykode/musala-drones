package com.musala.droneswebservices.payload;

import com.musala.droneswebservices.entity.Model;
import com.musala.droneswebservices.entity.State;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DroneDto {
    private Long id;
    private String serialNumber;
    private float weight;
    private int capacity;
    private Model model;
    private State state;
}

