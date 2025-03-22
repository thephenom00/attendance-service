//package cz.cvut.fel.attendance.service;
//
//import cz.cvut.fel.attendance.service.enums.Role;
//import cz.cvut.fel.attendance.service.model.Admin;
//import cz.cvut.fel.attendance.service.model.Child;
//import cz.cvut.fel.attendance.service.model.ChildAttendance;
//import cz.cvut.fel.attendance.service.model.Parent;
//import cz.cvut.fel.attendance.service.model.School;
//import cz.cvut.fel.attendance.service.model.Trainer;
//import cz.cvut.fel.attendance.service.model.Training;
//import cz.cvut.fel.attendance.service.model.TrainingUnit;
//import cz.cvut.fel.attendance.service.model.User;
//import cz.cvut.fel.attendance.service.repository.ChildAttendanceRepository;
//import cz.cvut.fel.attendance.service.repository.ChildRepository;
//import cz.cvut.fel.attendance.service.repository.SchoolRepository;
//import cz.cvut.fel.attendance.service.repository.TrainingRepository;
//import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
//import cz.cvut.fel.attendance.service.repository.UserRepository;
//import cz.cvut.fel.attendance.service.service.TrainingUnitService;
//import org.hibernate.Hibernate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DBinit {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private TrainingRepository trainingRepository;
//
//    @Autowired
//    private SchoolRepository schoolRepository;
//
//    @Autowired
//    private ChildRepository childRepository;
//
//    @Autowired
//    private TrainingUnitService trainingUnitService;
//
//    @Autowired
//    private TrainingUnitRepository trainingUnitRepository;
//
//
//    @Bean
//    @Transactional
//    public CommandLineRunner initDatabase() {
//        return args -> {
//            System.out.println("\n================== DATABASE INITIALIZATION STARTED ==================\n");
//
//            School school = new School(
//                    "ZŠ Štěnovice", "Školní 28", "Štěnovice",
//                    "Pavel Novák", "123456789", "Enter via the side door"
//            );
//            schoolRepository.save(school);
//
//            List<Training> trainings = List.of(
//                    new Training("Začátečnící", DayOfWeek.MONDAY, LocalTime.of(17, 30),
//                            LocalTime.of(19, 0), "2024/2025", 2300, 30),
//                    new Training("Pokročilí", DayOfWeek.TUESDAY, LocalTime.of(16, 00),
//                            LocalTime.of(17, 30), "2024/2025", 2500, 25)
//            );
//
//            trainings.forEach(training -> training.setSchool(school));
//            trainingRepository.saveAll(trainings);
//
//            List<Child> children = List.of(
//                    new Child("Teddy", "Riner", LocalDate.of(2015, 3, 10), "123 Maple St", "Paris", 75000, "123456/7890", trainings.get(0).getId()),
//                    new Child("Shohei", "Ono", LocalDate.of(2014, 7, 22), "456 Oak Ave", "Tokyo", 100000, "234567/8901", trainings.get(0).getId()),
//                    new Child("Clarisse", "Agbegnenou", LocalDate.of(2016, 1, 15), "789 Pine Rd", "Paris", 75001, "345678/9012", trainings.get(0).getId()),
//                    new Child("Lukas", "Krpalek", LocalDate.of(2013, 5, 19), "321 Birch Ln", "Prague", 11000, "456789/0123", trainings.get(1).getId()),
//                    new Child("Daria", "Bilodid", LocalDate.of(2012, 3, 29), "542 Boulevard", "Kyiv", 01001, "321312/2131", trainings.get(1).getId()),
//                    new Child("Rafaela", "Silva", LocalDate.of(2017, 8, 25), "555 Cedar Blvd", "Rio de Janeiro", 20000, "567890/1234", trainings.get(1).getId())
//            );
//
//
//            children.forEach(child -> {
//                Training training = trainings.stream().filter(t -> t.getId().equals(child.getRequestedTrainingId())).findFirst().orElse(null);
//                if (training != null) {
//                    child.setTraining(training);
//                    training.getChildren().add(child);
//                }
//            });
//
//            childRepository.saveAll(children);
//            trainingRepository.saveAll(trainings);
//
//            Trainer trainer = new Trainer("Kosei", "Inoue", "kosei@inoue.com", "+420 789 456 123", passwordEncoder.encode("kosei@inoue.com"));
//            userRepository.save(trainer);
//            trainings.get(0).addTrainer(trainer);
//            trainings.get(1).addTrainer(trainer);
//            trainer.addTraining(trainings.get(0));
//            trainer.addTraining(trainings.get(1));
//
//            trainingRepository.saveAll(trainings);
//
//            userRepository.save(new Admin("Karel", "Novak", "admin@gmail.com", "999", passwordEncoder.encode("123456")));
//
//            // Training One
//            Training firstTraining = trainings.get(0);
//            TrainingUnit upcomingTrainingUnit = new TrainingUnit();
//            upcomingTrainingUnit.setDate(LocalDate.of(3333,3,3));
//            upcomingTrainingUnit.setCurrent(true);
//            upcomingTrainingUnit.setTraining(firstTraining);
//            firstTraining.addTrainingUnit(upcomingTrainingUnit);
//            trainingUnitRepository.save(upcomingTrainingUnit);
//
//            for (int i=0; i<5; i++) {
//                TrainingUnit trainingUnit = new TrainingUnit();
//                trainingUnit.setDate(LocalDate.now().plusDays(i));
//                trainingUnit.setCurrent(false);
//                trainingUnit.setTraining(firstTraining);
//                trainingUnit.setDescription("Kliky kliky kliky kliky kliky kliky kliky ");
//                firstTraining.addTrainingUnit(trainingUnit);
//                trainingUnitRepository.save(trainingUnit);
//            }
//
//            // Training Two
//            Training secondTraining = trainings.get(1);
//            TrainingUnit upcomingTrainingUnitTwo = new TrainingUnit();
//            upcomingTrainingUnitTwo.setDate(LocalDate.of(4444,4,4));
//            upcomingTrainingUnitTwo.setCurrent(true);
//            upcomingTrainingUnitTwo.setTraining(secondTraining);
//            secondTraining.addTrainingUnit(upcomingTrainingUnitTwo);
//            trainingUnitRepository.save(upcomingTrainingUnitTwo);
//
//            for (int i=0; i<5; i++) {
//                TrainingUnit trainingUnit = new TrainingUnit();
//                trainingUnit.setDate(LocalDate.now().plusDays(i));
//                trainingUnit.setCurrent(false);
//                trainingUnit.setTraining(secondTraining);
//                trainingUnit.setDescription("Hop hop hop hop hop hop hop hop hop ");
//                secondTraining.addTrainingUnit(trainingUnit);
//                trainingUnitRepository.save(trainingUnit);
//            }
//
//
//
//            trainingUnitService.createWeeklyTrainingUnits();
//
//            System.out.println("\n================== DATABASE INITIALIZATION COMPLETED ==================\n");
//
//        };
//    }
//}
//
