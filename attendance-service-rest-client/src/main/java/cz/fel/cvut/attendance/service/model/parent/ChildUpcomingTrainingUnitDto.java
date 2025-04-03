package cz.fel.cvut.attendance.service.model.parent;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public record ChildUpcomingTrainingUnitDto(
        Long id,
        LocalDate date,
        DayOfWeek dayOfWeek,
        String name,
        String schoolName,
        LocalTime startTime,
        LocalTime endTime,

        String trainerFirstName,
        String trainerLastName,
        String trainerPhoneNumber
) {
}
