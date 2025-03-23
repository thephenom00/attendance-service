package cz.fel.cvut.attendance.service.model;


public record ReportDto(
        String date,
        String school,
        String name,
        double hours
) {
}
