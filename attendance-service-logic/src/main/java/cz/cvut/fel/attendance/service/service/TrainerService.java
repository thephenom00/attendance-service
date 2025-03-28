package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.TrainingUnitMapper;
import cz.cvut.fel.attendance.service.model.Trainer;
import cz.cvut.fel.attendance.service.model.TrainerAttendance;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.model.TrainingUnit;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.UserRepository;
import cz.fel.cvut.attendance.service.exception.UserException;
import cz.fel.cvut.attendance.service.model.ReportDto;
import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TrainerService {

    private final UserRepository userRepository;
    private final TrainingUnitMapper trainingUnitMapper;

    @Cacheable(value = "report", key = "#email")
    public List<ReportDto> getCurrentReport(String email) {
        Trainer trainer = (Trainer) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found.", HttpStatus.NOT_FOUND));

        List<TrainerAttendance> trainerAttendances = trainer.getAttendances();

        List<ReportDto> reports = new ArrayList<>();

        Month currentMonth = LocalDate.now().getMonth();

        for (TrainerAttendance attendance : trainerAttendances) {
            if (attendance.isPresent() && (attendance.getTrainingUnit().getDate().getMonth() == currentMonth)) {

                String date = attendance.getTrainingUnit().getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                String school = attendance.getTrainingUnit().getTraining().getSchool().getName();

                String name = attendance.getTrainingUnit().getTraining().getName();

                LocalTime startTime = attendance.getTrainingUnit().getTraining().getStartTime();
                LocalTime endTime = attendance.getTrainingUnit().getTraining().getEndTime();
                double hours = java.time.Duration.between(startTime, endTime).toMinutes();

                reports.add(new ReportDto(date, school, name, hours/60));
            }
        }

        return reports;
    }


    @Cacheable(value = "upcomingTrainingUnits", key = "#email")
    public List<TrainingUnitDto> getUpcomingTrainingUnits(String email) {
        Trainer trainer = (Trainer) userRepository.findByEmail(email).orElseThrow(()-> new UserException("User not found.", HttpStatus.NOT_FOUND));

        List<TrainingUnitDto> upcomingUnits = trainer.getTrainings().stream()
                .map(Training::getCurrentTrainingUnit)
                .filter(unit -> unit != null)
                .map(trainingUnitMapper::toDto)
                .toList();

        return upcomingUnits;
    }

    @Cacheable(value = "pastTrainingUnits", key = "#email")
    public List<TrainingUnitDto> getPastTrainingUnits(String email) {
        Trainer trainer = (Trainer) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found.", HttpStatus.NOT_FOUND));

        List<TrainingUnitDto> pastUnits = trainer.getTrainings().stream()
                .flatMap(training -> training.getPastTrainingUnits().stream())
                .map(trainingUnitMapper::toDto)
                .toList();

        return pastUnits;
    }

}
