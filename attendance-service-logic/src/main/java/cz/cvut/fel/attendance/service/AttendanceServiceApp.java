package cz.cvut.fel.attendance.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AttendanceServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceServiceApp.class, args);
	}

}
