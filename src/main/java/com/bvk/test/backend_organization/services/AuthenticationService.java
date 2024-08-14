package com.bvk.test.backend_organization.services;

import com.bvk.test.backend_organization.dtos.requests.LoginRequest;
import com.bvk.test.backend_organization.dtos.responses.AuthenticationResponse;
import com.bvk.test.backend_organization.dtos.responses.ResponseData;
import com.bvk.test.backend_organization.entities.User;
import com.bvk.test.backend_organization.repositories.UserRepository;
import com.bvk.test.backend_organization.securities.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseData<AuthenticationResponse> authenticated(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwt)
                .build();
        return ResponseData.<AuthenticationResponse>builder()
                .message("Successfully logged in")
                .data(authenticationResponse)
                .build();
    }
}
