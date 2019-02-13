package com.advertising.dashboard.mapper;

import com.advertising.dashboard.model.UserRole;
import com.advertising.dashboard.model.dto.UserDto;
import com.advertising.dashboard.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto maptoDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setActive(entity.getActive());
        dto.setEmail(entity.getEmail());
        dto.setUserRole(entity.getUserRole().toString());
        return dto;
    }

    public User mapToEntity(UserDto dto) {
        User entity = new User();
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setName(dto.getName());
        entity.setActive(dto.getActive());
        entity.setEmail(dto.getEmail());
        entity.setUserRole(UserRole.valueOf(dto.getUserRole().toUpperCase()));
        return entity;
    }
}
