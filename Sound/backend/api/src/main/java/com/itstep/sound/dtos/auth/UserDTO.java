package com.itstep.sound.dtos.auth;

import com.itstep.sound.models.auth.RolesEnum;
import com.itstep.sound.models.auth.UserModel;

import lombok.Data;

@Data
public class UserDTO {
    private final Long id;
    private final String username;
    private final String email;
    private final RolesEnum role;

    public UserDTO(UserModel user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        role = user.getRole();
    }
}