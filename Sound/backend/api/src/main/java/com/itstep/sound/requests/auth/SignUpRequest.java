package com.itstep.sound.requests.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request for registration")
public class SignUpRequest {
    @Schema(description = "Username", example = "john_doe")
    @Size(min = 5, max = 50, message = "The username must be between 5 and 50 characters long")
    @NotBlank(message = "The username cannot be empty")
    @Pattern(regexp = "^[a-z0-9_.]+$", message = "The username must only contain lowercase letters, numbers, underscores and dots")
    private String username;

    @Schema(description = "E-mail address", example = "johndoe@gmail.com")
    @Size(min = 5, max = 255, message = "The email address must contain between 5 and 255 characters")
    @NotBlank(message = "The e-mail address cannot be empty")
    @Email(message = "E-mail address should be in the format user@example.com")
    private String email;

    @Schema(description = "Password", example = "my_secret_password123")
    @Size(min = 5, max = 60, message = "The password must contain between 5 and 60 characters")
    @NotBlank(message = "The password address cannot be empty")
    private String password;
}