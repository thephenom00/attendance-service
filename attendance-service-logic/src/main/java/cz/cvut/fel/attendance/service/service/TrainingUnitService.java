package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.ChildAttendance;
import cz.cvut.fel.attendance.service.model.Trainer;
import cz.cvut.fel.attendance.service.model.TrainerAttendance;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.model.TrainingUnit;
import cz.cvut.fel.attendance.service.repository.ChildAttendanceRepository;
import cz.cvut.fel.attendance.service.repository.ChildRepository;
import cz.cvut.fel.attendance.service.repository.TrainerAttendanceRepository;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
import cz.cvut.fel.attendance.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TrainingUnitService {

    private final TrainingUnitRepository trainingUnitRepository;
    private final TrainingRepository trainingRepository;
    private final TrainerAttendanceRepository trainerAttendanceRepository;
    private final ChildAttendanceRepository childAttendanceRepository;

    private final UserRepository userRepository;
    private final ChildRepository childRepository;

//    @Scheduled(cron = "0 20 4 * * SUN") // s,m,h,dayOfMonth,month,dayOfWeek
    public void createWeeklyTrainingUnits() {
        List<Training> trainings = trainingRepository.findAll();
        LocalDate now = LocalDate.now();

        List<TrainingUnit> activeTrainingUnits = trainingUnitRepository.getTrainingUnitByCurrentIsTrue();
        activeTrainingUnits.forEach(unit -> unit.setCurrent(false));
        trainingUnitRepository.saveAll(activeTrainingUnits);

        for (Training training : trainings) {
            LocalDate trainingDate = now.with(TemporalAdjusters.next(training.getDayOfWeek()));
            List<Trainer> trainers = training.getTrainers();
            List<Child> children = training.getChildren();

            TrainingUnit trainingUnit = new TrainingUnit();
            trainingUnit.setDate(trainingDate);
            trainingUnit.setCurrent(true);
            trainingUnit.setTraining(training); // 0
            training.addTrainingUnit(trainingUnit); // 0

            for (Trainer trainer : trainers) {
                TrainerAttendance trainerAttendance = new TrainerAttendance();
                trainerAttendance.setTrainer(trainer); // +
                trainer.addTrainerAttendance(trainerAttendance); // +

                trainerAttendance.setTrainingUnit(trainingUnit); // -
                trainingUnit.addTrainerAttendance(trainerAttendance); // -

                trainerAttendance.setPresent(false);

                trainerAttendanceRepository.save(trainerAttendance);
                userRepository.save(trainer);
            }

            for (Child child : children) {
                ChildAttendance childAttendance = new ChildAttendance();
                childAttendance.setChild(child); // +
                child.addChildAttendance(childAttendance); // +

                childAttendance.setTrainingUnit(trainingUnit); // -
                trainingUnit.addChildAttendance(childAttendance); // -

                childAttendance.setPresent(false);

                childAttendanceRepository.save(childAttendance);
                childRepository.save(child);
            }

            trainingUnitRepository.save(trainingUnit);
            trainingRepository.save(training);
        }
        System.out.println("================= TRAINING UNITS GENERATED =================");
    }

}
