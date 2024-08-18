package com.bvk.test.backend_organization.services;

import com.bvk.test.backend_organization.dtos.requests.LoginRequest;
import com.bvk.test.backend_organization.dtos.requests.RegistrationRequest;
import com.bvk.test.backend_organization.dtos.responses.AuthenticationResponse;
import com.bvk.test.backend_organization.dtos.responses.ResponseData;
import com.bvk.test.backend_organization.dtos.responses.UserResponse;
import com.bvk.test.backend_organization.entities.User;
import com.bvk.test.backend_organization.enums.ResponseCode;
import com.bvk.test.backend_organization.enums.RoleEnum;
import com.bvk.test.backend_organization.repositories.UserRepository;
import com.bvk.test.backend_organization.securities.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public ResponseData<AuthenticationResponse> authenticated(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .user(new UserResponse(user.getId(), user.getName(), user.getEmail()))
                .token(jwt)
                .build();
        return ResponseData.<AuthenticationResponse>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(authenticationResponse)
                .build();
    }

    @Transactional
    public ResponseData<AuthenticationResponse> registration(RegistrationRequest request) {

        User user = userRepository.save(new User(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), RoleEnum.ADMIN));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), request.getPassword())
        );
        var jwt = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .user(new UserResponse(user.getId(), user.getName(), user.getEmail()))
                .token(jwt)
                .build();
        return ResponseData.<AuthenticationResponse>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(authenticationResponse)
                .build();
    }
}
