package com.musala.droneswebservices;

import com.musala.droneswebservices.entity.Drone;
import com.musala.droneswebservices.entity.Model;
import com.musala.droneswebservices.entity.State;
import com.musala.droneswebservices.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DronesWebServicesApplication implements CommandLineRunner {

	@Autowired
	private DroneRepository droneRepository;

	public static void main(String[] args) {
		SpringApplication.run(DronesWebServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Drone drone1 = new Drone();
		drone1.setSerialNumber("DR200101");
		drone1.setBatteryLevel(50);
		drone1.setModel(Model.CRUISERWEIGHT);
		drone1.setState(State.IDLE);
		droneRepository.save(drone1);

		Drone drone2 = new Drone();
		drone2.setSerialNumber("DR200102");
		drone2.setBatteryLevel(24);
		drone2.setModel(Model.CRUISERWEIGHT);
		drone2.setState(State.IDLE);
		droneRepository.save(drone2);

		Drone drone3 = new Drone();
		drone3.setSerialNumber("DR200103");
		drone3.setBatteryLevel(24);
		drone3.setModel(Model.LIGHTWEIGHT);
		drone3.setState(State.RETURNING);
		droneRepository.save(drone3);

		Drone drone4 = new Drone();
		drone4.setSerialNumber("DR200104");
		drone4.setBatteryLevel(60);
		drone4.setModel(Model.LIGHTWEIGHT);
		drone4.setState(State.DELIVERED);
		droneRepository.save(drone4);

	}
}
