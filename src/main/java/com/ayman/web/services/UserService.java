package com.ayman.web.services;

import com.ayman.web.dtos.RegistrationDto;
import com.ayman.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
