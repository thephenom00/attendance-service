package cz.fel.cvut.attendance.service.api;

import cz.fel.cvut.attendance.service.model.auth.AuthResponse;
import cz.fel.cvut.attendance.service.model.auth.LoginRequest;
import cz.fel.cvut.attendance.service.model.auth.ParentRegisterRequest;
import cz.fel.cvut.attendance.service.model.auth.TrainerRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/auth")
public interface UserApi {

    @PostMapping("/register/parent")
    @ResponseBody
    ResponseEntity<AuthResponse> registerParent(@RequestBody ParentRegisterRequest request);

    @PostMapping("/register/trainer")
    @ResponseBody
    ResponseEntity<AuthResponse> registerTrainer(@RequestBody TrainerRegisterRequest request);

    @GetMapping("/login")
    @ResponseBody
    ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request);

}
