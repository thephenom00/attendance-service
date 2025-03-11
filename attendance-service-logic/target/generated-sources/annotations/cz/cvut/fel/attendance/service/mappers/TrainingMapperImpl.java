package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.Training;
import cz.fel.cvut.attendance.service.model.TrainingDto;
import java.time.DayOfWeek;
import java.time.LocalTime;
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
public class TrainingMapperImpl implements TrainingMapper {

    @Override
    public TrainingDto toDto(Training trainingEntity) {
        if ( trainingEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        DayOfWeek dayOfWeek = null;
        LocalTime startTime = null;
        LocalTime endTime = null;
        String schoolYear = null;
        int price = 0;
        int capacity = 0;

        id = trainingEntity.getId();
        name = trainingEntity.getName();
        dayOfWeek = trainingEntity.getDayOfWeek();
        startTime = trainingEntity.getStartTime();
        endTime = trainingEntity.getEndTime();
        schoolYear = trainingEntity.getSchoolYear();
        price = trainingEntity.getPrice();
        capacity = trainingEntity.getCapacity();

        TrainingDto trainingDto = new TrainingDto( id, name, dayOfWeek, startTime, endTime, schoolYear, price, capacity );

        return trainingDto;
    }

    @Override
    public Training toEntity(TrainingDto trainingDto) {
        if ( trainingDto == null ) {
            return null;
        }

        Training training = new Training();

        training.setId( trainingDto.id() );
        training.setName( trainingDto.name() );
        training.setDayOfWeek( trainingDto.dayOfWeek() );
        training.setStartTime( trainingDto.startTime() );
        training.setEndTime( trainingDto.endTime() );
        training.setSchoolYear( trainingDto.schoolYear() );
        training.setPrice( trainingDto.price() );
        training.setCapacity( trainingDto.capacity() );

        return training;
    }

    @Override
    public List<TrainingDto> toDtoList(List<Training> trainings) {
        if ( trainings == null ) {
            return null;
        }

        List<TrainingDto> list = new ArrayList<TrainingDto>( trainings.size() );
        for ( Training training : trainings ) {
            list.add( toDto( training ) );
        }

        return list;
    }

    @Override
    public List<Training> toEntityList(List<TrainingDto> trainingDtos) {
        if ( trainingDtos == null ) {
            return null;
        }

        List<Training> list = new ArrayList<Training>( trainingDtos.size() );
        for ( TrainingDto trainingDto : trainingDtos ) {
            list.add( toEntity( trainingDto ) );
        }

        return list;
    }

    @Override
    public void updateTrainingFromDto(TrainingDto trainingDto, Training training) {
        if ( trainingDto == null ) {
            return;
        }

        if ( trainingDto.id() != null ) {
            training.setId( trainingDto.id() );
        }
        if ( trainingDto.name() != null ) {
            training.setName( trainingDto.name() );
        }
        if ( trainingDto.dayOfWeek() != null ) {
            training.setDayOfWeek( trainingDto.dayOfWeek() );
        }
        if ( trainingDto.startTime() != null ) {
            training.setStartTime( trainingDto.startTime() );
        }
        if ( trainingDto.endTime() != null ) {
            training.setEndTime( trainingDto.endTime() );
        }
        if ( trainingDto.schoolYear() != null ) {
            training.setSchoolYear( trainingDto.schoolYear() );
        }
        training.setPrice( trainingDto.price() );
        training.setCapacity( trainingDto.capacity() );
    }
}
