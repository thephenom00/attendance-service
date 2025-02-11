package cz.cvut.fel.attendance.service;

import cz.cvut.fel.attendance.service.repository.SchoolRepository;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
import cz.cvut.fel.attendance.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DBinit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingUnitRepository trainingUnitRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Transactional
    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
//            Trainer jakub = new Trainer("Jakub", "Novak", "john.doe@example.com", "1234567890", LocalDate.of(1985, 5, 15), "password123");
//            Trainer pavel = new Trainer("Pavel", "Svoboda", "pavel.svoboda@example.com", "1234467890", LocalDate.of(2000, 5, 15), "password23");
//
//            Judoka judoka1 = new Judoka("Tomas", "Mocka", "mike.jordan@example.com", "5555555555", LocalDate.of(2000, 3, 10), "password789");
//            Judoka judoka2 = new Judoka("Karel", "Mocka", "sarah.connor@example.com", "6666666666", LocalDate.of(1998, 12, 25), "password101");
//
//            userRepository.saveAll(Arrays.asList(jakub, pavel, judoka1, judoka2));
//
//            School zsCinska = new School();
//            zsCinska.setName("ZS Cinska");
//            zsCinska.setAddress("Cinska 123");
//            zsCinska.setCity("Brno");
//            zsCinska.setContactPerson("Petr Novak");
//            zsCinska.setContactNumber("1234567890");
//
//            schoolRepository.save(zsCinska);
//
//            TrainingAttendance trainingAttendance1 = new TrainingAttendance();
//            trainingAttendance1.setJudoka(judoka1);
//            trainingAttendance1.setPresent(true);
//
//            TrainingAttendance trainingAttendance2 = new TrainingAttendance();
//            trainingAttendance2.setJudoka(judoka2);
//            trainingAttendance2.setPresent(true);
//
//            trainingAttendanceRepository.saveAll(Arrays.asList(trainingAttendance1, trainingAttendance2));
//
//            TrainingUnit trainingUnit1 = new TrainingUnit();
//            trainingUnit1.setDate(LocalDate.of(2021, 10, 1));
//            trainingUnit1.setDescription("Test description");
//            trainingUnit1.setTrainingAttendances(List.of(trainingAttendance1, trainingAttendance2));
//            trainingUnit1.setTrainers(Arrays.asList(jakub, pavel));
//
//            TrainingUnit trainingUnit2 = new TrainingUnit();
//            trainingUnit2.setDate(LocalDate.of(2021, 10, 8));
//            trainingUnit2.setDescription("Test description 2");
//            trainingUnit1.setTrainers(Arrays.asList(jakub, pavel));
//
//            trainingUnitRepository.saveAll(List.of(trainingUnit1, trainingUnit2));
//
//            Training training1 = new Training();
//            training1.setTrainers(Arrays.asList(jakub, pavel));
//            training1.setJudokas(Arrays.asList(judoka1, judoka2));
//            training1.setSchool(zsCinska);
//            training1.setStartTime(LocalTime.of(18, 0));
//            training1.setEndTime(LocalTime.of(19, 30));
//            training1.setTrainingUnits(List.of(trainingUnit1, trainingUnit2));
//            trainingRepository.save(training1);

        };
    }
}

