package com.bvk.test.backend_organization.dtos.responses;

import com.bvk.test.backend_organization.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private UserResponse user;
    private String token;
}
