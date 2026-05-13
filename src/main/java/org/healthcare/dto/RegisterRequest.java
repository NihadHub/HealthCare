package org.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min=10)
    private String password;
}
