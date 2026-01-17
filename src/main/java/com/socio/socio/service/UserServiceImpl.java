package com.socio.socio.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socio.socio.dto.UserCreateRequestDto;
import com.socio.socio.dto.UserResponseDto;
import com.socio.socio.entity.ProfileType;
import com.socio.socio.entity.Role;
import com.socio.socio.entity.User;
import com.socio.socio.exception.UserNotFoundException;
import com.socio.socio.repository.UserRepository;
 
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository , PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponseDto mapToResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setDob(user.getDob());
        dto.setRole(user.getRole().name());
        dto.setProfileType(user.getProfileType().name());

        return dto;
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
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setDob(dto.getDob());
        user.setRole(Role.USER);
        user.setProfileType(ProfileType.PUBLIC);
        user.setCreatedAt(LocalDateTime.now());
        user.setPasswordUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll()
        .stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    public UserResponseDto getUserById(Long id){
        User user = userRepository.findById(id)
        .orElseThrow(()-> new UserNotFoundException("User of id:"+id+"Not found"));

        return mapToResponseDto(user);
    }
}

