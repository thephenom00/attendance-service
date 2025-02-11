package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.TrainingMapper;
import cz.cvut.fel.attendance.service.model.School;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.repository.SchoolRepository;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.fel.cvut.attendance.service.exception.SchoolException;
import cz.fel.cvut.attendance.service.exception.TrainingException;
import cz.fel.cvut.attendance.service.model.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;

    private final SchoolRepository schoolRepository;

    private final TrainingMapper trainingMapper;

    public TrainingDto createTraining(TrainingDto trainingDto, Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new SchoolException("School with ID " + schoolId + " not found", HttpStatus.NOT_FOUND));

        Training training = trainingMapper.toEntity(trainingDto);

        training.setSchool(school);
        school.getTrainings().add(training);

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

    public void deleteTraining(Long id) {
        if (!trainingRepository.existsById(id)) {
            throw new TrainingException("Training with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }

        trainingRepository.deleteById(id);
    }

    public TrainingDto updateTraining(Long id, TrainingDto trainingDto) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingException("Training with ID " + id + " not found.",
                        HttpStatus.NOT_FOUND));

        trainingMapper.updateTrainingFromDto(trainingDto, training);

        trainingRepository.save(training);
        return trainingMapper.toDto(training);
    }

}
