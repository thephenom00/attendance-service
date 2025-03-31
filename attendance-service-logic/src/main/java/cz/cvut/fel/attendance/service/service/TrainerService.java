package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.TrainingUnitMapper;
import cz.cvut.fel.attendance.service.model.Trainer;
import cz.cvut.fel.attendance.service.model.TrainerAttendance;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.model.TrainingUnit;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
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
    private final TrainingUnitRepository trainingUnitRepository;

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


    public List<TrainingUnitDto> getUpcomingTrainingUnits(String email) {
        List<TrainingUnit> upcomingUnits = trainingUnitRepository.findUpcomingUnitsByTrainerEmail(email);
        return upcomingUnits.stream()
                .map(trainingUnitMapper::toDto)
                .toList();
    }

    public List<TrainingUnitDto> getPastTrainingUnits(String email) {
        List<TrainingUnit> pastUnits = trainingUnitRepository.findPastUnitsByTrainerEmail(email);
        return pastUnits.stream()
                .map(trainingUnitMapper::toDto)
                .toList();
    }

}
