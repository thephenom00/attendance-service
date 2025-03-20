package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.TrainingUnitMapper;
import cz.cvut.fel.attendance.service.model.Trainer;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.UserRepository;
import cz.fel.cvut.attendance.service.exception.UserException;
import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TrainerService {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingUnitMapper trainingUnitMapper;

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
