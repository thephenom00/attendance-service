package cz.fel.cvut.attendance.service.model.auth;

import lombok.Data;
@Data
public class RefreshTokenRequestDto {
    private String refreshToken;
}