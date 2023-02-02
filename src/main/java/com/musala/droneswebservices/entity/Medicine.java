package com.musala.droneswebservices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
//    @Pattern(regexp = "[A-Za-z0-9-_]",message = "Only letters, numbers , underscores and dashes are allowed")
    private String name;
//    @Pattern(regexp = "[A-Z0-9-_]",message = "Only upper case letters, numbers , underscores and dashes are allowed")
    private String code;
    private float weight;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;


}
