package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.EventMapper;
import cz.cvut.fel.attendance.service.model.Event;
import cz.cvut.fel.attendance.service.repository.EventRepository;
import cz.fel.cvut.attendance.service.exception.EventException;
import cz.fel.cvut.attendance.service.exception.SchoolException;
import cz.fel.cvut.attendance.service.model.EventDto;
import cz.fel.cvut.attendance.service.model.trainer.EventRegisteredChildrenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public EventDto createEvent(EventDto eventDto) {
        if (eventRepository.existsByName(eventDto.name())) {
            throw new SchoolException("Event with name '" + eventDto.name() + "' already exists.",
                    HttpStatus.CONFLICT);
        }

        Event event = eventMapper.toEntity(eventDto);
        eventRepository.save(event);

        return eventMapper.toDto(event);
    }

    public EventDto getEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventException("Event with ID: " + id + " not found.", HttpStatus.NOT_FOUND));

        return eventMapper.toDto(event);
    }

    public List<EventDto> getEvents() {
        List<Event> events = eventRepository.findAll();
        return eventMapper.toDtoList(events);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventException("Event with ID: " + id + " is not existing.", HttpStatus.NOT_FOUND));

        eventRepository.delete(event);
    }

    public EventDto updateEvent(Long id, EventDto eventDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventException("Event with ID: " + id + " is not existing.", HttpStatus.NOT_FOUND));

        eventMapper.updateEventFromDto(eventDto, event);

        eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    public List<EventRegisteredChildrenDto> getRegisteredChildren(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventException("Event with ID: " + id + " is not existing.", HttpStatus.NOT_FOUND));

        List<EventRegisteredChildrenDto> children =  event.getChildren().stream()
                .map(child ->
                        new EventRegisteredChildrenDto(child.getFirstName() + " " + child.getLastName(),
                                child.getParent().getPhoneNumber(), child.getParent().getEmail()))
                .collect(Collectors.toList());

        return children;
    }
}
