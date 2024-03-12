package com.agencyamazon.service;

import com.agencyamazon.domain.dto.JwtAuthenticationResponse;
import com.agencyamazon.domain.dto.SignUpRequest;
import com.agencyamazon.domain.dto.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
