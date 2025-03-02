package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.ChildMapper;
import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.Event;
import cz.cvut.fel.attendance.service.model.Parent;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.model.User;
import cz.cvut.fel.attendance.service.repository.ChildRepository;
import cz.cvut.fel.attendance.service.repository.EventRepository;
import cz.cvut.fel.attendance.service.repository.TrainingRepository;
import cz.cvut.fel.attendance.service.repository.UserRepository;
import cz.fel.cvut.attendance.service.exception.ChildException;
import cz.fel.cvut.attendance.service.exception.EventException;
import cz.fel.cvut.attendance.service.exception.ParentException;
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

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final ChildMapper childMapper;

    public ChildDto addChildToTraining(Long id) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new ChildException("Child with ID " + id + " not found.", HttpStatus.NOT_FOUND));

        Training requestedTraining = trainingRepository.findById(child.getRequestedTrainingId())
                .orElseThrow(() -> new TrainingException("Training with ID " + child.getRequestedTrainingId() + " not found.", HttpStatus.NOT_FOUND));

        if (!requestedTraining.addChild(child)) {
            throw new TrainingException("Child is already registered to this training.", HttpStatus.CONFLICT);
        }

        child.setTraining(requestedTraining);
        child.setRequestedTrainingId(null);

        childRepository.save(child);
        trainingRepository.save(requestedTraining);

        return childMapper.toDto(child);
    }

    public void removeChildFromTraining(Long childId, Long trainingId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ChildException("Child with ID " + childId + " not found.", HttpStatus.NOT_FOUND));
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingException("Training with ID " + trainingId + " not found. ", HttpStatus.NOT_FOUND));

        if (!training.removeChild(child)) {
            throw new TrainingException("Child is was not registered to this Training", HttpStatus.FORBIDDEN);
        }
        child.setTraining(null);

        childRepository.save(child);
        trainingRepository.save(training);
    }


    public ChildDto registerChildToEvent(Long childId, Long eventId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ChildException("Child with ID " + childId + " not found.", HttpStatus.NOT_FOUND));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventException("Event with ID " + eventId + " not found. ", HttpStatus.NOT_FOUND));

        if (!child.addEvent(event) && !event.addChild(child)) {
            throw new EventException("Child is already registered to this Event", HttpStatus.CONFLICT);
        }

        childRepository.save(child);
        eventRepository.save(event);

        return childMapper.toDto(child);
    }

    public void unregisterChildFromEvent(Long childId, Long eventId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ChildException("Child with ID " + childId + " not found.", HttpStatus.NOT_FOUND));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventException("Event with ID " + eventId + " not found. ", HttpStatus.NOT_FOUND));

        if (!child.removeEvent(event) && !event.removeChild(child)) {
            throw new EventException("Child is was not registered to this Event", HttpStatus.FORBIDDEN);
        }

        childRepository.save(child);
        eventRepository.save(event);
    }

    public ChildDto createChild(ChildDto childDto) {
        User user = userRepository.findById(childDto.parentId())
                .orElseThrow(() -> new ParentException("User with ID: " + childDto.parentId() + " is not found.", HttpStatus.NOT_FOUND));

        if (!(user instanceof Parent parent)) {
            throw new ParentException("User with ID: " + childDto.parentId() + " is not a Parent.", HttpStatus.BAD_REQUEST);
        }

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

        userRepository.save(parent);
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
