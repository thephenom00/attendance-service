package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.School;
import cz.fel.cvut.attendance.service.model.SchoolDto;
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
public class SchoolMapperImpl implements SchoolMapper {

    @Override
    public SchoolDto toDto(School schoolEntity) {
        if ( schoolEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String address = null;
        String city = null;
        String contactPerson = null;
        String contactNumber = null;
        String instructions = null;

        id = schoolEntity.getId();
        name = schoolEntity.getName();
        address = schoolEntity.getAddress();
        city = schoolEntity.getCity();
        contactPerson = schoolEntity.getContactPerson();
        contactNumber = schoolEntity.getContactNumber();
        instructions = schoolEntity.getInstructions();

        SchoolDto schoolDto = new SchoolDto( id, name, address, city, contactPerson, contactNumber, instructions );

        return schoolDto;
    }

    @Override
    public School toEntity(SchoolDto schoolDto) {
        if ( schoolDto == null ) {
            return null;
        }

        School school = new School();

        school.setId( schoolDto.id() );
        school.setName( schoolDto.name() );
        school.setAddress( schoolDto.address() );
        school.setCity( schoolDto.city() );
        school.setContactPerson( schoolDto.contactPerson() );
        school.setContactNumber( schoolDto.contactNumber() );
        school.setInstructions( schoolDto.instructions() );

        return school;
    }

    @Override
    public List<SchoolDto> toDtoList(List<School> schools) {
        if ( schools == null ) {
            return null;
        }

        List<SchoolDto> list = new ArrayList<SchoolDto>( schools.size() );
        for ( School school : schools ) {
            list.add( toDto( school ) );
        }

        return list;
    }

    @Override
    public List<School> toEntityList(List<SchoolDto> schoolDtos) {
        if ( schoolDtos == null ) {
            return null;
        }

        List<School> list = new ArrayList<School>( schoolDtos.size() );
        for ( SchoolDto schoolDto : schoolDtos ) {
            list.add( toEntity( schoolDto ) );
        }

        return list;
    }

    @Override
    public void updateSchoolFromDto(SchoolDto schoolDto, School school) {
        if ( schoolDto == null ) {
            return;
        }

        if ( schoolDto.id() != null ) {
            school.setId( schoolDto.id() );
        }
        if ( schoolDto.name() != null ) {
            school.setName( schoolDto.name() );
        }
        if ( schoolDto.address() != null ) {
            school.setAddress( schoolDto.address() );
        }
        if ( schoolDto.city() != null ) {
            school.setCity( schoolDto.city() );
        }
        if ( schoolDto.contactPerson() != null ) {
            school.setContactPerson( schoolDto.contactPerson() );
        }
        if ( schoolDto.contactNumber() != null ) {
            school.setContactNumber( schoolDto.contactNumber() );
        }
        if ( schoolDto.instructions() != null ) {
            school.setInstructions( schoolDto.instructions() );
        }
    }
}
