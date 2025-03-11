package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.Parent;
import cz.fel.cvut.attendance.service.model.ChildDto;
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
public class ChildMapperImpl implements ChildMapper {

    @Override
    public ChildDto toDto(Child childEntity) {
        if ( childEntity == null ) {
            return null;
        }

        Long parentId = null;
        Long id = null;
        String firstName = null;
        String lastName = null;
        LocalDate dateOfBirth = null;
        String street = null;
        String city = null;
        int zip = 0;
        String birthNumber = null;
        Long requestedTrainingId = null;

        parentId = childEntityParentId( childEntity );
        id = childEntity.getId();
        firstName = childEntity.getFirstName();
        lastName = childEntity.getLastName();
        dateOfBirth = childEntity.getDateOfBirth();
        street = childEntity.getStreet();
        city = childEntity.getCity();
        zip = childEntity.getZip();
        birthNumber = childEntity.getBirthNumber();
        requestedTrainingId = childEntity.getRequestedTrainingId();

        ChildDto childDto = new ChildDto( id, firstName, lastName, dateOfBirth, street, city, zip, birthNumber, parentId, requestedTrainingId );

        return childDto;
    }

    @Override
    public Child toEntity(ChildDto childDto) {
        if ( childDto == null ) {
            return null;
        }

        Child child = new Child();

        child.setId( childDto.id() );
        child.setFirstName( childDto.firstName() );
        child.setLastName( childDto.lastName() );
        child.setDateOfBirth( childDto.dateOfBirth() );
        child.setStreet( childDto.street() );
        child.setCity( childDto.city() );
        child.setZip( childDto.zip() );
        child.setBirthNumber( childDto.birthNumber() );
        child.setRequestedTrainingId( childDto.requestedTrainingId() );

        return child;
    }

    @Override
    public List<ChildDto> toDtoList(List<Child> children) {
        if ( children == null ) {
            return null;
        }

        List<ChildDto> list = new ArrayList<ChildDto>( children.size() );
        for ( Child child : children ) {
            list.add( toDto( child ) );
        }

        return list;
    }

    @Override
    public List<Child> toEntityList(List<ChildDto> childDtos) {
        if ( childDtos == null ) {
            return null;
        }

        List<Child> list = new ArrayList<Child>( childDtos.size() );
        for ( ChildDto childDto : childDtos ) {
            list.add( toEntity( childDto ) );
        }

        return list;
    }

    @Override
    public void updateChildFromDto(ChildDto childDto, Child Child) {
        if ( childDto == null ) {
            return;
        }

        if ( childDto.id() != null ) {
            Child.setId( childDto.id() );
        }
        if ( childDto.firstName() != null ) {
            Child.setFirstName( childDto.firstName() );
        }
        if ( childDto.lastName() != null ) {
            Child.setLastName( childDto.lastName() );
        }
        if ( childDto.dateOfBirth() != null ) {
            Child.setDateOfBirth( childDto.dateOfBirth() );
        }
        if ( childDto.street() != null ) {
            Child.setStreet( childDto.street() );
        }
        if ( childDto.city() != null ) {
            Child.setCity( childDto.city() );
        }
        Child.setZip( childDto.zip() );
        if ( childDto.birthNumber() != null ) {
            Child.setBirthNumber( childDto.birthNumber() );
        }
        if ( childDto.requestedTrainingId() != null ) {
            Child.setRequestedTrainingId( childDto.requestedTrainingId() );
        }
    }

    private Long childEntityParentId(Child child) {
        if ( child == null ) {
            return null;
        }
        Parent parent = child.getParent();
        if ( parent == null ) {
            return null;
        }
        Long id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
