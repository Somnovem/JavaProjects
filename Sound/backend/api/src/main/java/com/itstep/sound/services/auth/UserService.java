package com.itstep.sound.services.auth;

import com.itstep.sound.exceptions.StatusException;
import com.itstep.sound.models.auth.UserModel;
import com.itstep.sound.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserModel save(UserModel user) {
        return repository.save(user);
    }

    public UserModel create(UserModel user) throws StatusException {
        if (repository.existsByUsername(user.getUsername())) {
            throw new StatusException("A user with this name already exists", HttpStatus.CONFLICT);
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new StatusException("A user with this email already exists", HttpStatus.CONFLICT);
        }

        return this.save(user);
    }

    public UserModel getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public UserModel getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}