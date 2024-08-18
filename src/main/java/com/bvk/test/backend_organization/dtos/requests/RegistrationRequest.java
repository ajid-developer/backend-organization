package com.bvk.test.backend_organization.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class RegistrationRequest {
    @NotNull(message = "Name tidak boleh null")
    @NotBlank(message = "Name harus diisi")
    private String name;
    @NotNull(message = "Email tidak boleh null")
    @NotBlank(message = "Email harus diisi")
    @Email(message = "Email tidak valid")
    private String email;
    @NotNull(message = "Password tidak boleh null")
    @NotBlank(message = "Password harus diisi")
    private String password;
}
