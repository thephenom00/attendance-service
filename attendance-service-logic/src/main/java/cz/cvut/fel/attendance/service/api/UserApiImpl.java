package cz.cvut.fel.attendance.service.api;

import cz.cvut.fel.attendance.service.config.JwtUtil;
import cz.cvut.fel.attendance.service.model.User;
import cz.cvut.fel.attendance.service.service.UserService;
import cz.fel.cvut.attendance.service.api.UserApi;
import cz.fel.cvut.attendance.service.model.auth.AuthResponse;
import cz.fel.cvut.attendance.service.model.auth.LoginRequest;
import cz.fel.cvut.attendance.service.model.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<AuthResponse> registerParent(RegisterRequest request) {
        String email = request.email();
        String password = request.password();

        userService.registerParent(request);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final UserDetails userDetails = userService.loadUserByUsername(email);

        final String accessToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(accessToken, email));
    }

    @Override
    public ResponseEntity<AuthResponse> registerTrainer(RegisterRequest request) {
        String email = request.email();
        String password = request.password();

        userService.registerTrainer(request);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final UserDetails userDetails = userService.loadUserByUsername(email);

        final String accessToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(accessToken, email));
    }

    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        String email = request.email();
        String password = request.password();
        User user = userService.login(email, password);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        final UserDetails userDetails = userService.loadUserByUsername(email);
        final String accessToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(accessToken, user.getEmail()));
    }
}
