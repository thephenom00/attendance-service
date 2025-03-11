package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.model.Parent;
import cz.cvut.fel.attendance.service.model.Trainer;
import cz.cvut.fel.attendance.service.model.User;
import cz.cvut.fel.attendance.service.repository.UserRepository;
import cz.fel.cvut.attendance.service.exception.UserException;
import cz.fel.cvut.attendance.service.model.auth.AuthResponse;
import cz.fel.cvut.attendance.service.model.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService  {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void registerParent(RegisterRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserException("User with email: " + request.email() + " already exists.", HttpStatus.CONFLICT);
        }

        Parent parent = new Parent(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.phoneNumber(),
                passwordEncoder.encode(request.password())
        );

        try {
            userRepository.save(parent);
        } catch (Exception e) {
            throw new UserException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public void registerTrainer(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserException("User with email: " + request.email() + " already exists.", HttpStatus.CONFLICT);
        }

        Trainer trainer = new Trainer(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.phoneNumber(),
                passwordEncoder.encode(request.password())
        );

        try {
            userRepository.save(trainer);
        } catch (Exception e) {
            throw new UserException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("User with email: " + email + " not found.", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new UserException("Invalid credentials. Please check your email and password.", HttpStatus.UNAUTHORIZED);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        List<GrantedAuthority> authorities = List.of(() -> user.getRole().toString());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
