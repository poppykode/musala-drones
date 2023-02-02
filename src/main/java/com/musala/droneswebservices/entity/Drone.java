package com.musala.droneswebservices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drones",uniqueConstraints = {@UniqueConstraint(columnNames = {"serialNumber"})})
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, message = "serial number is too short requires a minmum of 2 characters")
    @Size(max = 100, message = "serial number can not be longer than 100 characters}")
    private String serialNumber;
    private float weight;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private Model model;
    @Enumerated(EnumType.STRING)
    private  State state;
    @OneToMany(mappedBy = "drone",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Medicine> medicines;
 }
