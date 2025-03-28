package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.ChildAttendanceMapper;
import cz.cvut.fel.attendance.service.mappers.TrainerAttendanceMapper;
import cz.cvut.fel.attendance.service.mappers.TrainingUnitMapper;
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
import cz.fel.cvut.attendance.service.exception.TrainingUnitException;
import cz.fel.cvut.attendance.service.model.ChildAttendanceDto;
import cz.fel.cvut.attendance.service.model.TrainerAttendanceDto;
import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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

    private final TrainingUnitMapper trainingUnitMapper;
    private final ChildAttendanceMapper childAttendanceMapper;
    private final TrainerAttendanceMapper trainerAttendanceMapper;

    @Cacheable(value = "upcomingTrainingUnits", key = "#id")
    public TrainingUnitDto getTrainingUnit(Long id) {
        TrainingUnit trainingUnit = trainingUnitRepository.findById(id)
                .orElseThrow(() -> new TrainingUnitException("Training Unit with ID " + id + " not found", HttpStatus.NOT_FOUND));

        return trainingUnitMapper.toDto(trainingUnit);
    }

    @CacheEvict(value = { "pastTrainingUnits", "upcomingTrainingUnits" }, allEntries = true)
    public TrainingUnitDto updateDescription(Long id, String description) {
        TrainingUnit trainingUnit = trainingUnitRepository.findById(id)
                .orElseThrow(() -> new TrainingUnitException("Training Unit with ID " + id + " not found", HttpStatus.NOT_FOUND));

        trainingUnit.setDescription(description);
        trainingUnitRepository.save(trainingUnit);

        return trainingUnitMapper.toDto(trainingUnit);
    }

    @Cacheable(value = "childAttendance", key = "#id")
    public List<ChildAttendanceDto> getChildAttendances(Long id) {
        TrainingUnit trainingUnit = trainingUnitRepository.findById(id)
                .orElseThrow(() -> new TrainingUnitException("Training Unit with ID " + id + " not found", HttpStatus.NOT_FOUND));

        List<ChildAttendance> childAttendances = trainingUnit.getChildAttendances();

        return childAttendanceMapper.toDtoList(childAttendances);
    }

    @Cacheable(value = "trainerAttendance", key = "#id")
    public List<TrainerAttendanceDto> getTrainerAttendances(Long id) {
        TrainingUnit trainingUnit = trainingUnitRepository.findById(id)
                .orElseThrow(() -> new TrainingUnitException("Training Unit with ID " + id + " not found", HttpStatus.NOT_FOUND));

        List<TrainerAttendance> trainerAttendances = trainingUnit.getTrainerAttendances();

        return trainerAttendanceMapper.toDtoList(trainerAttendances);
    }


//    @Scheduled(cron = "0 20 4 * * SUN") // s,m,h,dayOfMonth,month,dayOfWeek
    @CacheEvict(value = { "pastTrainingUnits", "upcomingTrainingUnits" }, allEntries = true)
    public void createWeeklyTrainingUnits() {
        List<Training> trainings = trainingRepository.findAll();
        LocalDate now = LocalDate.now();

        List<TrainingUnit> activeTrainingUnits = trainingUnitRepository.findByCurrentIsTrue();
        activeTrainingUnits.forEach(unit -> unit.setCurrent(false));
        trainingUnitRepository.saveAll(activeTrainingUnits);

        for (Training training : trainings) {
            LocalDate trainingDate = now.with(TemporalAdjusters.next(training.getDayOfWeek()));
            List<Trainer> trainers = training.getTrainers();
            List<Child> children = training.getChildren();

            TrainingUnit trainingUnit = new TrainingUnit();
            trainingUnit.setDate(trainingDate);
            trainingUnit.setCurrent(true);
            trainingUnit.setTraining(training);
            training.addTrainingUnit(trainingUnit);

            for (Trainer trainer : trainers) {
                TrainerAttendance trainerAttendance = new TrainerAttendance();
                trainerAttendance.setTrainer(trainer);
                trainer.addTrainerAttendance(trainerAttendance);

                trainerAttendance.setTrainingUnit(trainingUnit);
                trainingUnit.addTrainerAttendance(trainerAttendance);

                trainerAttendance.setPresent(false);

                trainerAttendanceRepository.save(trainerAttendance);
                userRepository.save(trainer);
            }

            for (Child child : children) {
                ChildAttendance childAttendance = new ChildAttendance();
                childAttendance.setChild(child);
                child.addChildAttendance(childAttendance);

                childAttendance.setTrainingUnit(trainingUnit);
                trainingUnit.addChildAttendance(childAttendance);

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
