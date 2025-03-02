package cz.fel.cvut.attendance.service.model.auth;

public record TrainerRegisterRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String password
) {
}
