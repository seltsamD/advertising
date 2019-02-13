package com.advertising.dashboard.service.impl;

import com.advertising.dashboard.config.UserDetail;
import com.advertising.dashboard.dao.UserDao;
import com.advertising.dashboard.exception.UserNotFoundException;
import com.advertising.dashboard.mapper.UserMapper;
import com.advertising.dashboard.model.UserRole;
import com.advertising.dashboard.model.dto.UserDto;
import com.advertising.dashboard.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    public UserDto findById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            return userMapper.maptoDto(userOptional.get());
        } else {
            throw new UserNotFoundException(String.format("Couldn't find user with id %s", id));
        }
    }

    public List<UserDto> findAll() {
        return StreamSupport.stream(userDao.findAll().spliterator(), false)
                .map(user -> userMapper.maptoDto(user))
                .collect(Collectors.toList());
    }


    public UserDto findByEmail(String email) {
        return userMapper.maptoDto(userDao.findByEmail(email));
    }


    public UserDto save(UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        return userMapper.maptoDto(userDao.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (null == user) {
            throw new UsernameNotFoundException(String.format("Couldn't find user with id %s", email));
        }
        return new UserDetail(user);
    }

    @PostConstruct
    private void setupDefaultUser() {
        //-- just to make sure there is an ADMIN user exist in the database for testing purpose
        if (userDao.count() == 0) {
            userDao.save(new User("Admin Name", "admin@i.ua", passwordEncoder.encode("admin"), UserRole.ADMIN.toString(), true));
            userDao.save(new User("Admin Non active", "admin2@i.ua", passwordEncoder.encode("admin"), UserRole.ADMIN.toString(), false));
        }
    }
}
