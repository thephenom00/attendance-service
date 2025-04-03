package cz.cvut.fel.attendance.service.mappers;

import cz.cvut.fel.attendance.service.model.TrainingUnit;
import cz.fel.cvut.attendance.service.model.parent.ChildUpcomingTrainingUnitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChildUpcomingTrainingUnitMapper {
    @Mapping(source = "training.dayOfWeek", target = "dayOfWeek")
    @Mapping(source = "training.school.name", target = "schoolName")
    @Mapping(source = "training.startTime", target = "startTime")
    @Mapping(source = "training.endTime", target = "endTime")
    @Mapping(source = "training.name", target = "name")
    @Mapping(expression = "java(trainingUnitEntity.getTraining().getTrainers().get(0).getFirstName())", target = "trainerFirstName")
    @Mapping(expression = "java(trainingUnitEntity.getTraining().getTrainers().get(0).getLastName())", target = "trainerLastName")
    @Mapping(expression = "java(trainingUnitEntity.getTraining().getTrainers().get(0).getPhoneNumber())", target = "trainerPhoneNumber")
    ChildUpcomingTrainingUnitDto toDto(TrainingUnit trainingUnitEntity);
}
