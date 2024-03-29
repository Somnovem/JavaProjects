package com.itstep.sound.repositories;

import com.itstep.sound.models.auth.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}