package com.itstep.sound.services.auth;

import com.itstep.sound.dtos.auth.UserDTO;
import com.itstep.sound.exceptions.StatusException;
import com.itstep.sound.models.auth.RoleEnum;
import com.itstep.sound.models.auth.UserModel;
import com.itstep.sound.requests.auth.SignInRequest;
import com.itstep.sound.requests.auth.SignUpRequest;
import com.itstep.sound.responses.auth.JwtAuthenticationResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) throws StatusException {
        UserModel user = UserModel.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.ROLE_USER)
                .build();

        userService.create(user);

        String jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt, new UserDTO(user));
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) throws StatusException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new StatusException(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        UserModel user = userService.getByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt, new UserDTO(user));
    }
}