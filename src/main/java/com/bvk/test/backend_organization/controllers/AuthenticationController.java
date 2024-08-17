package com.bvk.test.backend_organization.controllers;

import com.bvk.test.backend_organization.dtos.requests.LoginRequest;
import com.bvk.test.backend_organization.dtos.responses.AuthenticationResponse;
import com.bvk.test.backend_organization.dtos.responses.ResponseData;
import com.bvk.test.backend_organization.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseData<AuthenticationResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authenticationService.authenticated(loginRequest));
    }
}
