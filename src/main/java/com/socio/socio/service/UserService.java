package com.socio.socio.service;

import java.util.List;

import com.socio.socio.dto.UserCreateRequestDto;
import com.socio.socio.dto.UserResponseDto;
import com.socio.socio.entity.User;

public interface UserService {
    User createUser(UserCreateRequestDto dto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);
}
