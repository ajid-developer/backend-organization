package com.bvk.test.backend_organization.controllers;

import com.bvk.test.backend_organization.dtos.requests.RegistrationRequest;
import com.bvk.test.backend_organization.dtos.responses.AuthenticationResponse;
import com.bvk.test.backend_organization.dtos.responses.ResponseData;
import com.bvk.test.backend_organization.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<ResponseData<AuthenticationResponse>> registration(@Valid @RequestBody RegistrationRequest request) {

        return ResponseEntity.ok(authenticationService.registration(request));
    }

//    @GetMapping
//    public String profile(Principal principal) {
//
//        log.info("principadate -> {}", principal.toString());
//
//        return principal.getName();
//    }
}
