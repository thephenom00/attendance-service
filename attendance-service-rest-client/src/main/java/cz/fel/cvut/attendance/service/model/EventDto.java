package cz.fel.cvut.attendance.service.model;

import java.time.LocalDate;

public record EventDto (
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        int freePlaces,
        int price,
        String description
){}
