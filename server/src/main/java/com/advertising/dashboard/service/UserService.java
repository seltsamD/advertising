package com.advertising.dashboard.service;

import com.advertising.dashboard.exception.UserNotFoundException;
import com.advertising.dashboard.model.UserRole;
import com.advertising.dashboard.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto findByEmail(String email) throws UserNotFoundException;

    UserDto findById(Integer id) throws UserNotFoundException;

    List<UserDto> findAllActive();

    @Transactional
    UserDto save(UserDto userDto);

    @Transactional
    UserDto update(Integer id, UserDto userDto);

    @Transactional
    void delete(Integer id);

    boolean isEmailUnique(String email);

    List<UserDto> findByRoles(List<UserRole> userRoles);

}
