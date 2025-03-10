package cz.fel.cvut.attendance.service.model;


public record ParentContactDto(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
