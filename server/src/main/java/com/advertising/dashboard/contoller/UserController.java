package com.advertising.dashboard.contoller;

import com.advertising.dashboard.exception.UserNotFoundException;
import com.advertising.dashboard.model.dto.UserDto;
import com.advertising.dashboard.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping
    public UserDto findById(HttpServletRequest request) throws UserNotFoundException {
        String name = request.getUserPrincipal().getName();
        return userService.findByEmail(name);
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto user) {
        return userService.save(user);
    }
}
