package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.Child;
import cz.cvut.fel.attendance.service.model.ChildAttendance;
import cz.fel.cvut.attendance.service.model.ChildAttendanceDto;
import cz.fel.cvut.attendance.service.model.ChildDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChildAttendanceMapper {
    @Mapping(source = "child.firstName", target = "firstName")
    @Mapping(source = "child.lastName", target = "lastName")
    ChildAttendanceDto toDto(ChildAttendance childAttendanceEntity);

    ChildAttendance toEntity(ChildAttendanceDto childAttendanceDto);

    List<ChildAttendanceDto> toDtoList(List<ChildAttendance> childAttendances);
    List<ChildAttendance> toEntityList(List<ChildAttendanceDto> childAttendanceDtos);
   }
