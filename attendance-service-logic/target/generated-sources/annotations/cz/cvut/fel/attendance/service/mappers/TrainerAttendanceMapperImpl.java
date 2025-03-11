package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.Trainer;
import cz.cvut.fel.attendance.service.model.TrainerAttendance;
import cz.fel.cvut.attendance.service.model.TrainerAttendanceDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-12T00:50:46+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class TrainerAttendanceMapperImpl implements TrainerAttendanceMapper {

    @Override
    public TrainerAttendanceDto toDto(TrainerAttendance trainerAttendanceEntity) {
        if ( trainerAttendanceEntity == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        Long id = null;
        boolean present = false;

        firstName = trainerAttendanceEntityTrainerFirstName( trainerAttendanceEntity );
        lastName = trainerAttendanceEntityTrainerLastName( trainerAttendanceEntity );
        id = trainerAttendanceEntity.getId();
        present = trainerAttendanceEntity.isPresent();

        TrainerAttendanceDto trainerAttendanceDto = new TrainerAttendanceDto( id, firstName, lastName, present );

        return trainerAttendanceDto;
    }

    @Override
    public TrainerAttendance toEntity(TrainerAttendanceDto trainerAttendanceDto) {
        if ( trainerAttendanceDto == null ) {
            return null;
        }

        TrainerAttendance trainerAttendance = new TrainerAttendance();

        trainerAttendance.setId( trainerAttendanceDto.id() );
        trainerAttendance.setPresent( trainerAttendanceDto.present() );

        return trainerAttendance;
    }

    @Override
    public List<TrainerAttendanceDto> toDtoList(List<TrainerAttendance> trainerAttendances) {
        if ( trainerAttendances == null ) {
            return null;
        }

        List<TrainerAttendanceDto> list = new ArrayList<TrainerAttendanceDto>( trainerAttendances.size() );
        for ( TrainerAttendance trainerAttendance : trainerAttendances ) {
            list.add( toDto( trainerAttendance ) );
        }

        return list;
    }

    @Override
    public List<TrainerAttendance> toEntityList(List<TrainerAttendanceDto> trainerAttendanceDtos) {
        if ( trainerAttendanceDtos == null ) {
            return null;
        }

        List<TrainerAttendance> list = new ArrayList<TrainerAttendance>( trainerAttendanceDtos.size() );
        for ( TrainerAttendanceDto trainerAttendanceDto : trainerAttendanceDtos ) {
            list.add( toEntity( trainerAttendanceDto ) );
        }

        return list;
    }

    private String trainerAttendanceEntityTrainerFirstName(TrainerAttendance trainerAttendance) {
        if ( trainerAttendance == null ) {
            return null;
        }
        Trainer trainer = trainerAttendance.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        String firstName = trainer.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String trainerAttendanceEntityTrainerLastName(TrainerAttendance trainerAttendance) {
        if ( trainerAttendance == null ) {
            return null;
        }
        Trainer trainer = trainerAttendance.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        String lastName = trainer.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }
}
