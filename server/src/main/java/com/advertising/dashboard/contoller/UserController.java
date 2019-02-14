package com.advertising.dashboard.contoller;

import com.advertising.dashboard.exception.UserNotFoundException;
import com.advertising.dashboard.model.dto.UserDto;
import com.advertising.dashboard.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/all")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping
    public UserDto findById(HttpServletRequest request) throws UserNotFoundException {
        String name = request.getUserPrincipal().getName();
        return userService.findByEmail(name);
    }

    @GetMapping("/id/{id}")
    public UserDto findById(@PathVariable(value = "id") Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable(value = "id") Long id,
                          @RequestBody UserDto user) throws UserNotFoundException {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) throws UserNotFoundException {
        userService.delete(id);
    }
}
