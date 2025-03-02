//package cz.cvut.fel.attendance.service.service;
//
//import cz.cvut.fel.attendance.service.config.JwtUtil;
//import cz.cvut.fel.attendance.service.enums.Role;
//import cz.cvut.fel.attendance.service.model.Parent;
//import cz.cvut.fel.attendance.service.model.User;
//import cz.cvut.fel.attendance.service.repository.UserRepository;
//import cz.fel.cvut.attendance.service.exception.UserException;
//import cz.fel.cvut.attendance.service.model.auth.AuthResponse;
//import cz.fel.cvut.attendance.service.model.auth.LoginRequest;
//import cz.fel.cvut.attendance.service.model.auth.ParentRegisterRequest;
//import cz.fel.cvut.attendance.service.model.auth.TrainerRegisterRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService  {
//
//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final JwtUtil jwtUtil;
//
//    private final AuthenticationManager authenticationManager;
//
//    public AuthResponse registerParent(ParentRegisterRequest request) {
//
//        String email = request.email();
//
//        if (userRepository.findByEmail(email).isPresent()) {
//            throw new UserException("User with email: " + email + " already exists.", HttpStatus.CONFLICT);
//        }
//
//        String password = request.password();
//
//        Parent parent = new Parent(
//                request.firstName(),
//                request.lastName(),
//                email,
//                request.phoneNumber(),
//                passwordEncoder.encode(password),
//                request.street(),
//                request.city(),
//                request.zip(),
//                Role.PARENT
//        );
//        userRepository.save(parent);
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(email, password)
//        );
//
//        final UserDetails userDetails = loadUserByUsername(email);
//
//        final String accessToken = jwtUtil.generateToken(userDetails);
//        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
//
//        return new AuthResponse(accessToken, refreshToken, email);
//    }
//
//    public AuthResponse registerTrainer(TrainerRegisterRequest request) {
//        return null;
//    }
//
//    public AuthResponse login(LoginRequest request) {
//        return null;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
//
//        List<GrantedAuthority> authorities = List.of(() -> user.getRole().toString());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//}
