package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.ChildMapper;
import cz.cvut.fel.attendance.service.mappers.TrainingMapper;
import cz.cvut.fel.attendance.service.mappers.TrainingUnitMapper;
import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.School;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.model.TrainingUnit;
import cz.cvut.fel.attendance.service.repository.SchoolRepository;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
import cz.fel.cvut.attendance.service.exception.SchoolException;
import cz.fel.cvut.attendance.service.exception.TrainingException;
import cz.fel.cvut.attendance.service.model.ChildDto;
import cz.fel.cvut.attendance.service.model.TrainingDto;
import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TrainingService {

    private final TrainingRepository trainingRepository;

    private final SchoolRepository schoolRepository;

    private final TrainingMapper trainingMapper;

    private final ChildMapper childMapper;

    private final TrainingUnitMapper trainingUnitMapper;

    public TrainingDto createTraining(TrainingDto trainingDto, Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new SchoolException("School with ID " + schoolId + " not found", HttpStatus.NOT_FOUND));

        Training training = trainingMapper.toEntity(trainingDto);

        training.setSchool(school);
        school.addTraining(training);

        Training savedTraining = trainingRepository.save(training);

        return trainingMapper.toDto(savedTraining);
    }

    public TrainingDto getTraining(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingException("Training with ID " + id + " not found", HttpStatus.NOT_FOUND));

        return trainingMapper.toDto(training);
    }

    public List<TrainingDto> getTrainings() {
        List<Training> trainings = trainingRepository.findAll();

        return trainingMapper.toDtoList(trainings);
    }

    public TrainingUnitDto getCurrentTrainingUnit(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingException("Training with ID " + id + " not found", HttpStatus.NOT_FOUND));

        List<TrainingUnit> trainingUnits = training.getTrainingUnits();
        TrainingUnit currentTrainingUnit = trainingUnits.stream()
                .filter(t -> t.isCurrent())
                .findFirst()
                .orElseThrow(() -> new TrainingException("No active training unit found", HttpStatus.NOT_FOUND));

        return trainingUnitMapper.toDto(currentTrainingUnit);
    }

    public List<TrainingUnitDto> getPastTrainingUnits(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingException("Training with ID " + id + " not found", HttpStatus.NOT_FOUND));

        List<TrainingUnit> trainingUnits = training.getTrainingUnits();
        List<TrainingUnit> pastTrainingUnits = trainingUnits.stream()
                .filter(t -> !t.isCurrent())
                .toList();

        return trainingUnitMapper.toDtoList(pastTrainingUnits);
    }

    public List<TrainingUnitDto> getTrainingUnits(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingException("Training with ID " + id + " not found", HttpStatus.NOT_FOUND));

        return trainingUnitMapper.toDtoList(training.getTrainingUnits());
    }

    public void deleteTraining(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingException("Training with ID " + id + " not found", HttpStatus.NOT_FOUND));

        trainingRepository.delete(training);
    }

    public TrainingDto updateTraining(Long id, TrainingDto trainingDto) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingException("Training with ID " + id + " not found.",
                        HttpStatus.NOT_FOUND));

        trainingMapper.updateTrainingFromDto(trainingDto, training);

        trainingRepository.save(training);
        return trainingMapper.toDto(training);
    }

    public List<ChildDto> getChildren(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingException("Training with ID " + id + " not found.",
                        HttpStatus.NOT_FOUND));

        List<Child> children = training.getChildren();

        return childMapper.toDtoList(children);
    }

}
