package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.ChildMapper;
import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.Parent;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.repository.ChildRepository;
import cz.cvut.fel.attendance.service.repository.ParentRepository;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.fel.cvut.attendance.service.exception.ChildException;
import cz.fel.cvut.attendance.service.exception.ParentException;
import cz.fel.cvut.attendance.service.exception.SchoolException;
import cz.fel.cvut.attendance.service.exception.TrainingException;
import cz.fel.cvut.attendance.service.model.ChildDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;

    private final TrainingRepository trainingRepository;

    private final ParentRepository parentRepository;

    private final ChildMapper childMapper;

    public ChildDto addChildToTraining(Long id) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new ChildException("Child with ID " + id + " not found.", HttpStatus.NOT_FOUND));

        Training requestedTraining = trainingRepository.findById(child.getRequestedTrainingId())
                .orElseThrow(() -> new TrainingException("Training with ID " + child.getRequestedTrainingId() + " not found.", HttpStatus.NOT_FOUND));

        child.setTraining(requestedTraining);
        child.setRequestedTrainingId(null);
        requestedTraining.addChild(child);

        childRepository.save(child);
        trainingRepository.save(requestedTraining);

        return childMapper.toDto(child);
    }

    public ChildDto createChild(ChildDto childDto) {
        Parent parent = parentRepository.findById(childDto.parentId())
                .orElseThrow(() -> new ParentException("Parent with ID: " + childDto.parentId() + " is not found.", HttpStatus.NOT_FOUND));

        Child child = childMapper.toEntity(childDto);

        if (childRepository.existsByFirstName(child.getFirstName()) &
                childRepository.existsByLastName(child.getLastName())) {
            throw new ChildException("Child " + child.getFirstName() + " " + child.getLastName() + " is already registered.",
                    HttpStatus.CONFLICT);
        } else if (childRepository.existsByBirthNumber(child.getBirthNumber())) {
            throw new ChildException("Child with birth number " + child.getBirthNumber() + " is already registered.",
                    HttpStatus.CONFLICT);
        }

        parent.addChild(child);
        child.setParent(parent);

        parentRepository.save(parent);
        childRepository.save(child);
        return childMapper.toDto(child);
    }

    public ChildDto getChild(Long id) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new ChildException("Child with ID " + id + " not found.", HttpStatus.NOT_FOUND));

        return childMapper.toDto(child);
    }

    public List<ChildDto> getChildren() {
        List<Child> children = childRepository.findAll();

        return childMapper.toDtoList(children);
    }

    public void deleteChild(Long id) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new ChildException("Child with ID " + id + " not found.", HttpStatus.NOT_FOUND));

        childRepository.delete(child);
    }

    public ChildDto updateChild(Long id, ChildDto childDto) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new ChildException("Child with ID " + id + " not found.", HttpStatus.NOT_FOUND));

        childMapper.updateChildFromDto(childDto, child);

        childRepository.save(child);
        return childMapper.toDto(child);
    }
}
