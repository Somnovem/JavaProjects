package com.itstep.sound.responses.auth;

import com.itstep.sound.dtos.auth.UserDTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response that contains token and certain info about user")
public class JwtAuthenticationResponse {
    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String token;

    private UserDTO user;
}