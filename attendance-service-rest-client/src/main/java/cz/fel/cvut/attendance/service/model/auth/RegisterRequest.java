package cz.fel.cvut.attendance.service.model.auth;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String password
) {}
