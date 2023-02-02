package com.musala.droneswebservices.payload;

import com.musala.droneswebservices.entity.Drone;
import lombok.Data;

@Data
public class MedicineDto {
    private  Long id;
    private String name;
    private String code;
    private String image;
    private float weight;
    private Long droneId;

}
