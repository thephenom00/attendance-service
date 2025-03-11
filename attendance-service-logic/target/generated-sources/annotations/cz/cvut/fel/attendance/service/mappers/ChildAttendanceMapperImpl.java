package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.ChildAttendance;
import cz.fel.cvut.attendance.service.model.ChildAttendanceDto;
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
public class ChildAttendanceMapperImpl implements ChildAttendanceMapper {

    @Override
    public ChildAttendanceDto toDto(ChildAttendance childAttendanceEntity) {
        if ( childAttendanceEntity == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        Long id = null;
        boolean present = false;

        firstName = childAttendanceEntityChildFirstName( childAttendanceEntity );
        lastName = childAttendanceEntityChildLastName( childAttendanceEntity );
        id = childAttendanceEntity.getId();
        present = childAttendanceEntity.isPresent();

        ChildAttendanceDto childAttendanceDto = new ChildAttendanceDto( id, firstName, lastName, present );

        return childAttendanceDto;
    }

    @Override
    public ChildAttendance toEntity(ChildAttendanceDto childAttendanceDto) {
        if ( childAttendanceDto == null ) {
            return null;
        }

        ChildAttendance childAttendance = new ChildAttendance();

        childAttendance.setId( childAttendanceDto.id() );
        childAttendance.setPresent( childAttendanceDto.present() );

        return childAttendance;
    }

    @Override
    public List<ChildAttendanceDto> toDtoList(List<ChildAttendance> childAttendances) {
        if ( childAttendances == null ) {
            return null;
        }

        List<ChildAttendanceDto> list = new ArrayList<ChildAttendanceDto>( childAttendances.size() );
        for ( ChildAttendance childAttendance : childAttendances ) {
            list.add( toDto( childAttendance ) );
        }

        return list;
    }

    @Override
    public List<ChildAttendance> toEntityList(List<ChildAttendanceDto> childAttendanceDtos) {
        if ( childAttendanceDtos == null ) {
            return null;
        }

        List<ChildAttendance> list = new ArrayList<ChildAttendance>( childAttendanceDtos.size() );
        for ( ChildAttendanceDto childAttendanceDto : childAttendanceDtos ) {
            list.add( toEntity( childAttendanceDto ) );
        }

        return list;
    }

    private String childAttendanceEntityChildFirstName(ChildAttendance childAttendance) {
        if ( childAttendance == null ) {
            return null;
        }
        Child child = childAttendance.getChild();
        if ( child == null ) {
            return null;
        }
        String firstName = child.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String childAttendanceEntityChildLastName(ChildAttendance childAttendance) {
        if ( childAttendance == null ) {
            return null;
        }
        Child child = childAttendance.getChild();
        if ( child == null ) {
            return null;
        }
        String lastName = child.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }
}
