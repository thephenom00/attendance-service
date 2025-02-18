package cz.cvut.fel.attendance.service;

import cz.cvut.fel.attendance.service.enums.Role;
import cz.cvut.fel.attendance.service.model.Parent;
import cz.cvut.fel.attendance.service.repository.ParentRepository;
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

    @Autowired
    private ParentRepository parentRepository;

    @Transactional
    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            Parent parent = new Parent("Pavel", "Novak", "novak@gmail.com",
                    "123", "abc", "Street", "Prague", 123, Role.PARENT);
            parentRepository.save(parent);

        };
    }
}

