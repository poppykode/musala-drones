package com.musala.droneswebservices.payload;

import com.musala.droneswebservices.entity.Model;
import com.musala.droneswebservices.entity.State;
import lombok.Data;

@Data
public class DroneDto {
    private Long id;
    private String serialNumber;
    private int capacity;
    private Model model;
    private State state;
}

