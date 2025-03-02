//package cz.cvut.fel.attendance.service.api;
//
//import cz.cvut.fel.attendance.service.service.UserService;
//import cz.fel.cvut.attendance.service.api.UserApi;
//import cz.fel.cvut.attendance.service.model.auth.AuthResponse;
//import cz.fel.cvut.attendance.service.model.auth.LoginRequest;
//import cz.fel.cvut.attendance.service.model.auth.ParentRegisterRequest;
//import cz.fel.cvut.attendance.service.model.auth.TrainerRegisterRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//public class UserApiImpl implements UserApi {
//
//    private final UserService userService;
//
//    @Override
//    public ResponseEntity<AuthResponse> registerParent(ParentRegisterRequest request) {
//        return ResponseEntity.ok(userService.registerParent(request));
//    }
//
//    @Override
//    public ResponseEntity<AuthResponse> registerTrainer(TrainerRegisterRequest request) {
//        return ResponseEntity.ok(userService.registerTrainer(request));
//    }
//
//    @Override
//    public ResponseEntity<AuthResponse> login(LoginRequest request) {
//        return ResponseEntity.ok(userService.login(request));
//    }
//}
