package com.musala.droneswebservices.repository;

import com.musala.droneswebservices.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    List<Medicine> findByDroneId(Long id);

}
