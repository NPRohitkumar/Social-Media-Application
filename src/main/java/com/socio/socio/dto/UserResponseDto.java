package com.socio.socio.dto;

import java.time.LocalDate;

public class UserResponseDto {
    private Long id;
    private String email;
    private LocalDate dob;
    private String role;
    private String profileType;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getProfileType() {
        return profileType;
    }
    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }


}
