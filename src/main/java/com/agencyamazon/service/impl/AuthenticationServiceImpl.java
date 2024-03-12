package com.agencyamazon.service.impl;


import com.agencyamazon.domain.dto.JwtAuthenticationResponse;
import com.agencyamazon.domain.dto.SigninRequest;
import com.agencyamazon.domain.dto.SignUpRequest;
import com.agencyamazon.domain.model.user.AppUser;
import com.agencyamazon.repository.UserRepository;
import com.agencyamazon.service.AuthenticationService;
import com.agencyamazon.service.JwtService;
import com.agencyamazon.service.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(final SignUpRequest request) {
        createUserValidation(request);
        AppUser appUser = new AppUser(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));
        userRepository.save(appUser);

        var jwt = jwtService.generateToken(appUser);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private void createUserValidation(final SignUpRequest request) {
        if (request == null) {
            throw new RuntimeException("request is empty"); // todo create custom ex
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("AppUser with email %s already exists".formatted(request.getEmail()));
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("AppUser with username %s already exists".formatted(request.getUsername()));
        }
    }


    @Override
    public JwtAuthenticationResponse signin(final SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
