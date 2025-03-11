package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.Event;
import cz.fel.cvut.attendance.service.model.EventDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-12T00:50:47+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDto toDto(Event eventEntity) {
        if ( eventEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        int freePlaces = 0;
        int price = 0;
        String description = null;

        id = eventEntity.getId();
        name = eventEntity.getName();
        startDate = eventEntity.getStartDate();
        endDate = eventEntity.getEndDate();
        freePlaces = eventEntity.getFreePlaces();
        price = eventEntity.getPrice();
        description = eventEntity.getDescription();

        EventDto eventDto = new EventDto( id, name, startDate, endDate, freePlaces, price, description );

        return eventDto;
    }

    @Override
    public Event toEntity(EventDto eventDto) {
        if ( eventDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setId( eventDto.id() );
        event.setName( eventDto.name() );
        event.setStartDate( eventDto.startDate() );
        event.setEndDate( eventDto.endDate() );
        event.setFreePlaces( eventDto.freePlaces() );
        event.setPrice( eventDto.price() );
        event.setDescription( eventDto.description() );

        return event;
    }

    @Override
    public List<EventDto> toDtoList(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventDto> list = new ArrayList<EventDto>( events.size() );
        for ( Event event : events ) {
            list.add( toDto( event ) );
        }

        return list;
    }

    @Override
    public List<Event> toEntityList(List<EventDto> eventDtos) {
        if ( eventDtos == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( eventDtos.size() );
        for ( EventDto eventDto : eventDtos ) {
            list.add( toEntity( eventDto ) );
        }

        return list;
    }

    @Override
    public void updateEventFromDto(EventDto eventDto, Event event) {
        if ( eventDto == null ) {
            return;
        }

        if ( eventDto.id() != null ) {
            event.setId( eventDto.id() );
        }
        if ( eventDto.name() != null ) {
            event.setName( eventDto.name() );
        }
        if ( eventDto.startDate() != null ) {
            event.setStartDate( eventDto.startDate() );
        }
        if ( eventDto.endDate() != null ) {
            event.setEndDate( eventDto.endDate() );
        }
        event.setFreePlaces( eventDto.freePlaces() );
        event.setPrice( eventDto.price() );
        if ( eventDto.description() != null ) {
            event.setDescription( eventDto.description() );
        }
    }
}
