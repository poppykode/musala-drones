package com.musala.droneswebservices.payload;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MedicineDto {
    private  Long id;
    @Pattern(regexp = "[A-Za-z0-9\\-\\_]",message = "Only letters, numbers , underscores and dashes are allowed")
    private String name;
    @Pattern(regexp = "[A-Z0-9\\-\\_]+",message = "Only upper case letters, numbers , underscores and dashes are allowed")
    private String code;
    private String image;
    private float weight;
    private Long droneId;

}
