package com.socio.socio.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.socio.socio.dto.UserCreateRequestDto;
import com.socio.socio.entity.ProfileType;
import com.socio.socio.entity.Role;
import com.socio.socio.entity.User;
import com.socio.socio.repository.UserRepository;
 
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserCreateRequestDto dto){
        if(dto.getDob() == null){
            throw new RuntimeException("Date of birth is mandatory");
        }

        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("user already exists with this email");
        }

        //Defualts
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setDob(dto.getDob());
        user.setRole(Role.USER);
        user.setProfileType(ProfileType.PUBLIC);
        user.setCreatedAt(LocalDateTime.now());
        user.setPasswordUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}

