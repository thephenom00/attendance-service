package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.School;
import cz.cvut.fel.attendance.service.model.Training;
import cz.cvut.fel.attendance.service.model.TrainingUnit;
import cz.fel.cvut.attendance.service.model.TrainingUnitDto;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
public class TrainingUnitMapperImpl implements TrainingUnitMapper {

    @Override
    public TrainingUnitDto toDto(TrainingUnit trainingUnitEntity) {
        if ( trainingUnitEntity == null ) {
            return null;
        }

        DayOfWeek dayOfWeek = null;
        String schoolName = null;
        LocalTime startTime = null;
        LocalTime endTime = null;
        Long id = null;
        LocalDate date = null;
        String description = null;

        dayOfWeek = trainingUnitEntityTrainingDayOfWeek( trainingUnitEntity );
        schoolName = trainingUnitEntityTrainingSchoolName( trainingUnitEntity );
        startTime = trainingUnitEntityTrainingStartTime( trainingUnitEntity );
        endTime = trainingUnitEntityTrainingEndTime( trainingUnitEntity );
        id = trainingUnitEntity.getId();
        date = trainingUnitEntity.getDate();
        description = trainingUnitEntity.getDescription();

        TrainingUnitDto trainingUnitDto = new TrainingUnitDto( id, date, description, dayOfWeek, schoolName, startTime, endTime );

        return trainingUnitDto;
    }

    @Override
    public TrainingUnit toEntity(TrainingUnitDto trainingUnitDto) {
        if ( trainingUnitDto == null ) {
            return null;
        }

        TrainingUnit trainingUnit = new TrainingUnit();

        trainingUnit.setId( trainingUnitDto.id() );
        trainingUnit.setDate( trainingUnitDto.date() );
        trainingUnit.setDescription( trainingUnitDto.description() );

        return trainingUnit;
    }

    @Override
    public List<TrainingUnitDto> toDtoList(List<TrainingUnit> trainingUnits) {
        if ( trainingUnits == null ) {
            return null;
        }

        List<TrainingUnitDto> list = new ArrayList<TrainingUnitDto>( trainingUnits.size() );
        for ( TrainingUnit trainingUnit : trainingUnits ) {
            list.add( toDto( trainingUnit ) );
        }

        return list;
    }

    @Override
    public List<TrainingUnit> toEntityList(List<TrainingUnitDto> trainingUnitDtos) {
        if ( trainingUnitDtos == null ) {
            return null;
        }

        List<TrainingUnit> list = new ArrayList<TrainingUnit>( trainingUnitDtos.size() );
        for ( TrainingUnitDto trainingUnitDto : trainingUnitDtos ) {
            list.add( toEntity( trainingUnitDto ) );
        }

        return list;
    }

    @Override
    public void updateTrainingUnitFromDto(TrainingUnitDto trainingUnitDto, Training training) {
        if ( trainingUnitDto == null ) {
            return;
        }

        if ( trainingUnitDto.id() != null ) {
            training.setId( trainingUnitDto.id() );
        }
        if ( trainingUnitDto.dayOfWeek() != null ) {
            training.setDayOfWeek( trainingUnitDto.dayOfWeek() );
        }
        if ( trainingUnitDto.startTime() != null ) {
            training.setStartTime( trainingUnitDto.startTime() );
        }
        if ( trainingUnitDto.endTime() != null ) {
            training.setEndTime( trainingUnitDto.endTime() );
        }
    }

    private DayOfWeek trainingUnitEntityTrainingDayOfWeek(TrainingUnit trainingUnit) {
        if ( trainingUnit == null ) {
            return null;
        }
        Training training = trainingUnit.getTraining();
        if ( training == null ) {
            return null;
        }
        DayOfWeek dayOfWeek = training.getDayOfWeek();
        if ( dayOfWeek == null ) {
            return null;
        }
        return dayOfWeek;
    }

    private String trainingUnitEntityTrainingSchoolName(TrainingUnit trainingUnit) {
        if ( trainingUnit == null ) {
            return null;
        }
        Training training = trainingUnit.getTraining();
        if ( training == null ) {
            return null;
        }
        School school = training.getSchool();
        if ( school == null ) {
            return null;
        }
        String name = school.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private LocalTime trainingUnitEntityTrainingStartTime(TrainingUnit trainingUnit) {
        if ( trainingUnit == null ) {
            return null;
        }
        Training training = trainingUnit.getTraining();
        if ( training == null ) {
            return null;
        }
        LocalTime startTime = training.getStartTime();
        if ( startTime == null ) {
            return null;
        }
        return startTime;
    }

    private LocalTime trainingUnitEntityTrainingEndTime(TrainingUnit trainingUnit) {
        if ( trainingUnit == null ) {
            return null;
        }
        Training training = trainingUnit.getTraining();
        if ( training == null ) {
            return null;
        }
        LocalTime endTime = training.getEndTime();
        if ( endTime == null ) {
            return null;
        }
        return endTime;
    }
}
