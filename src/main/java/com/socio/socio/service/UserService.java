package com.socio.socio.service;

import com.socio.socio.dto.UserCreateRequestDto;
import com.socio.socio.entity.User;

public interface UserService {
    User createUser(UserCreateRequestDto dto);
}
