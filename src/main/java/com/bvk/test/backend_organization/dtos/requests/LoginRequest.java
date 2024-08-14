package com.bvk.test.backend_organization.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Email tidak valid")
    @NotBlank(message = "Email harus diisi")
    private String email;
    @NotBlank(message = "Password harus diisi")
    private String password;
}
