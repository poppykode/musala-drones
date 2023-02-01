package com.musala.droneswebservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Each **Medication** has:
//        - name (allowed only letters, numbers, ‘-‘, ‘_’);
//        - weight;
//        - code (allowed only upper case letters, underscore and numbers);
//        - image (picture of the medication case).
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private String code;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id")
    private Drone drone;

}
