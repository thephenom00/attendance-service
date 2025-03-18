package cz.cvut.fel.attendance.service;

import cz.cvut.fel.attendance.service.enums.Role;
import cz.cvut.fel.attendance.service.model.Admin;
import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.ChildAttendance;
import cz.cvut.fel.attendance.service.model.Parent;
import cz.cvut.fel.attendance.service.model.School;
import cz.cvut.fel.attendance.service.model.Trainer;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.model.User;
import cz.cvut.fel.attendance.service.repository.ChildAttendanceRepository;
import cz.cvut.fel.attendance.service.repository.ChildRepository;
import cz.cvut.fel.attendance.service.repository.SchoolRepository;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
import cz.cvut.fel.attendance.service.repository.UserRepository;
import cz.cvut.fel.attendance.service.service.TrainingUnitService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DBinit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private TrainingUnitService trainingUnitService;


    @Bean
    @Transactional
    public CommandLineRunner initDatabase() {
        return args -> {
            System.out.println("\n================== DATABASE INITIALIZATION STARTED ==================\n");

            // Create School
            School school = new School(
                    "Judo Academy", "123 Main Street", "Prague",
                    "John Doe", "+420 123 456 789", "Enter via the side door"
            );
            schoolRepository.save(school);

            // List of Trainings
            List<Training> trainings = List.of(
                    new Training("1", DayOfWeek.MONDAY, LocalTime.of(17, 30),
                            LocalTime.of(19, 0), "2024/2025", 2300, 30),
                    new Training("2", DayOfWeek.TUESDAY, LocalTime.of(16, 00),
                            LocalTime.of(17, 30), "2024/2025", 2500, 25),
                    new Training("3", DayOfWeek.WEDNESDAY, LocalTime.of(18, 00),
                            LocalTime.of(19, 30), "2024/2025", 2700, 20),
                    new Training("4", DayOfWeek.THURSDAY, LocalTime.of(17, 00),
                            LocalTime.of(19, 00), "2024/2025", 3000, 15),
                    new Training("5", DayOfWeek.FRIDAY, LocalTime.of(10, 00),
                            LocalTime.of(11, 30), "2024/2025", 2000, 20)
            );

            // Assign School to Trainings
            trainings.forEach(training -> training.setSchool(school));
            trainingRepository.saveAll(trainings);

            // Create Children
            List<Child> children = List.of(
                    new Child("Emma", "Smith", LocalDate.of(2015, 3, 10), "123 Maple St", "New York", 10001, "123456/7890", trainings.get(0).getId()),
                    new Child("Noah", "Johnson", LocalDate.of(2014, 7, 22), "456 Oak Ave", "Los Angeles", 90012, "234567/8901", trainings.get(1).getId()),
                    new Child("Liam", "Brown", LocalDate.of(2016, 1, 15), "789 Pine Rd", "Chicago", 60601, "345678/9012", trainings.get(2).getId()),
                    new Child("Sophia", "Williams", LocalDate.of(2013, 5, 19), "321 Birch Ln", "Houston", 77002, "456789/0123", trainings.get(3).getId()),
                    new Child("Ethan", "Davis", LocalDate.of(2017, 8, 25), "555 Cedar Blvd", "San Francisco", 94102, "567890/1234", trainings.get(4).getId())
            );

            children.forEach(child -> {
                Training training = trainings.stream().filter(t -> t.getId().equals(child.getRequestedTrainingId())).findFirst().orElse(null);
                if (training != null) {
                    child.setTraining(training);
                    training.getChildren().add(child);
                }
            });

            childRepository.saveAll(children);
            trainingRepository.saveAll(trainings);

            // Create Trainers
            List<Trainer> trainers = List.of(
                    new Trainer("David", "Sobek", "david@example.com", "+420 789 456 123", "securePass123"),
                    new Trainer("Jiri", "Stepan", "jiri@example.com", "+420 321 654 987", "strongPass456"),
                    new Trainer("Marek", "Novak", "marek@example.com", "+420 123 789 654", "safePass789"),
                    new Trainer("Tomas", "Kratochvil", "tomas@example.com", "+420 987 654 321", "passWord123"),
                    new Trainer("Lucas", "Benes", "lucas@example.com", "+420 741 852 963", "trainerPass456")
            );

            // Assign Trainers to Trainings
            for (int i = 0; i < trainers.size(); i++) {
                Trainer trainer = trainers.get(i);
                Training assignedTraining = trainings.get(i % trainings.size()); // Rotate trainers
                trainer.getTrainings().add(assignedTraining);
                assignedTraining.getTrainers().add(trainer);
            }

            userRepository.saveAll(trainers);
            trainingRepository.saveAll(trainings);

            userRepository.save(new Admin("Karel", "Novak", "admin@gmail.com", "999", passwordEncoder.encode("123")));

            System.out.println("✅ Trainers assigned to trainings:");


            trainingUnitService.createWeeklyTrainingUnits();

            System.out.println("\n================== DATABASE INITIALIZATION COMPLETED ==================\n");

        };
    }
}

