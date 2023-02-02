package com.musala.droneswebservices.payload;

import jakarta.validation.constraints.Pattern;
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
