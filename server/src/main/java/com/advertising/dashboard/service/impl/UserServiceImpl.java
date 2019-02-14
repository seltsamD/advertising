package com.advertising.dashboard.service.impl;

import com.advertising.dashboard.config.UserDetail;
import com.advertising.dashboard.dao.UserDao;
import com.advertising.dashboard.exception.UserNotFoundException;
import com.advertising.dashboard.mapper.UserMapper;
import com.advertising.dashboard.model.UserRole;
import com.advertising.dashboard.model.dto.UserDto;
import com.advertising.dashboard.model.entity.User;
import com.advertising.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findByEmail(String email) throws UserNotFoundException {
        User user = userDao.findByEmail(email);
        if (null == user) {
            throw new UserNotFoundException(String.format("Couldn't find user with email %s", email));
        }

        return userMapper.maptoDto(user);
    }

    @Override
    public UserDto findById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            return userMapper.maptoDto(userOptional.get());
        } else {
            throw new UserNotFoundException(String.format("Couldn't find user with id %s", id));
        }
    }

    @Override
    public List<UserDto> findAll() {
        return userDao.findAllByActiveTrue().stream()
                .map(user -> userMapper.maptoDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        return userMapper.maptoDto(userDao.save(user));
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        Optional<User> userOptional = userDao.findById(id);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            userDao.save(user);
        } else {
            user = userMapper.mapToEntity(userDto);
            userDao.save(user);
        }
        return userMapper.maptoDto(user);
    }

    @Override
    public void delete(Long id) {
        Optional<User> userOptional = userDao.findById(id);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setActive(false);
            userDao.save(user);
        }
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
