package com.agencyamazon.controller;


import com.agencyamazon.domain.dto.JwtAuthenticationResponse;
import com.agencyamazon.domain.dto.SigninRequest;
import com.agencyamazon.domain.dto.SignUpRequest;
import com.agencyamazon.service.impl.AuthenticationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody final SignUpRequest request) {
        JwtAuthenticationResponse response = authenticationServiceImpl.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody final SigninRequest request) {
        JwtAuthenticationResponse createdAppUser = authenticationServiceImpl.signin(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(createdAppUser);
    }



}
