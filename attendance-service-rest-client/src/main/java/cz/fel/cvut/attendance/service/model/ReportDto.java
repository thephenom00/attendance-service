package cz.fel.cvut.attendance.service.model;

import java.time.LocalDate;

public record ReportDto(
        String name,
        LocalDate date,
        String school,
        int rate
) {
}
