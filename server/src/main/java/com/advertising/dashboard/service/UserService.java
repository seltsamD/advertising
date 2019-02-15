package com.advertising.dashboard.service;

import com.advertising.dashboard.exception.UserNotFoundException;
import com.advertising.dashboard.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto findByEmail(String email) throws UserNotFoundException;

    UserDto findById(Integer id) throws UserNotFoundException;

    List<UserDto> findAll();

    UserDto save(UserDto userDto);

    UserDto update(Integer id, UserDto userDto);

    void delete(Integer id);

    boolean isEmailUnique(String email);

    List<UserDto> findAdopsAndPublishers();

}
