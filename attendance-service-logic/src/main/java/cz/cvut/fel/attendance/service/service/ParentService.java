package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.ChildMapper;
import cz.cvut.fel.attendance.service.mappers.ChildUpcomingTrainingUnitMapper;
import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.Parent;
import cz.cvut.fel.attendance.service.model.Trainer;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.model.TrainingUnit;
import cz.cvut.fel.attendance.service.model.User;
import cz.cvut.fel.attendance.service.repository.ChildRepository;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
import cz.cvut.fel.attendance.service.repository.UserRepository;
import cz.fel.cvut.attendance.service.exception.ChildException;
import cz.fel.cvut.attendance.service.exception.ParentException;
import cz.fel.cvut.attendance.service.exception.TrainingException;
import cz.fel.cvut.attendance.service.exception.UserException;
import cz.fel.cvut.attendance.service.model.ChildDto;
import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
import cz.fel.cvut.attendance.service.model.parent.ChildUpcomingTrainingUnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParentService {

    private final TrainingUnitRepository trainingUnitRepository;
    private final ChildRepository childRepository;
    private final TrainingRepository trainingRepository;

    private final ChildUpcomingTrainingUnitMapper childUpcomingTrainingUnitMapper;
    private final UserRepository userRepository;
    private final ChildMapper childMapper;

    public List<ChildUpcomingTrainingUnitDto> getUpcomingTrainingUnits(String email) {
        List<TrainingUnit> trainingUnits = trainingUnitRepository.findUpcomingUnitsByParentEmail(email);
        return trainingUnits.stream()
                .map(childUpcomingTrainingUnitMapper::toDto)
                .toList();
    }

    public List<ChildDto> getChildren(String email) {
        Parent parent = (Parent) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Parent not found.", HttpStatus.NOT_FOUND));

        List<Child> children = parent.getChildren();

        return childMapper.toDtoList(children);
    }

    public ChildDto createChild(String email, ChildDto childDto) {
        Parent parent = (Parent) userRepository.findByEmail(email)
                .orElseThrow(() -> new ParentException("User with ID: " + email + " is not found.", HttpStatus.NOT_FOUND));

        if (childRepository.existsByBirthNumber(childDto.birthNumber())) {
            throw new ChildException("Child with birth number " + childDto.birthNumber() + " is already registered.",
                    HttpStatus.CONFLICT);
        }

        Child child = childMapper.toEntity(childDto);

        Training training = trainingRepository.findById(childDto.requestedTrainingId()).orElseThrow(()-> new TrainingException("Training with ID: " + childDto.requestedTrainingId() + " not found.", HttpStatus.NOT_FOUND));

        if ((training.getCapacity() - 1) < 0) {
            throw new TrainingException("Child cannot be added to training, because it has full capacity.", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        }

        training.setCapacity(training.getCapacity() - 1);
        parent.addChild(child);
        child.setParent(parent);

        trainingRepository.save(training);
        userRepository.save(parent);
        childRepository.save(child);
        return childMapper.toDto(child);
    }
}
