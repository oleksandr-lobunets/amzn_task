package com.agencyamazon.service;


import com.agencyamazon.domain.dto.JwtAuthenticationResponse;
import com.agencyamazon.domain.dto.SigninRequest;
import com.agencyamazon.domain.dto.SignUpRequest;
import com.agencyamazon.domain.model.user.AppUser;
import com.agencyamazon.repository.UserRepository;
import com.agencyamazon.security.JwtTokenUtil;
import com.agencyamazon.service.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
//    private final JwtTokenUtil jwtTokenUtil;


    public AppUser createUser(final SignUpRequest request) {
        createUserValidation(request);
        AppUser appUser = new AppUser(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));
        return userRepository.save(appUser);
    }

    private void createUserValidation(final SignUpRequest request) {
        if (request == null) {
            throw new RuntimeException("request is empty"); // todo create custom ex
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("AppUser with email %s already exists".formatted(request.getEmail()));
        }
        if ( findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("AppUser with username %s already exists".formatted(request.getUsername()));
        }
    }

    public Optional<AppUser> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    public JwtAuthenticationResponse signIn(SigninRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateToken(authentication);

        return new JwtAuthenticationResponse(jwt);
    }
}
