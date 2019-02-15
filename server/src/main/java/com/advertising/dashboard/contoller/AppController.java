package com.advertising.dashboard.contoller;

import com.advertising.dashboard.exception.AppNotFoundException;
import com.advertising.dashboard.model.dto.AppDto;
import com.advertising.dashboard.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/app")
public class AppController {
    @Autowired
    private AppService appService;

    @GetMapping("/{id}")
    public AppDto findById(@PathVariable("id") Integer id) throws AppNotFoundException {
        return appService.findById(id);
    }

    @GetMapping("/all")
    public List<AppDto> findAll() {
        return appService.findAll();
    }

    @PostMapping
    public AppDto save(@RequestBody AppDto appDto) {
        return appService.save(appDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        appService.delete(id);
    }

    @PutMapping
    public AppDto update(@RequestBody AppDto appDto) {
        return appService.update(appDto);
    }

    @GetMapping("/user")
    public List<AppDto> findByCurrentUser(HttpServletRequest request) {
        String email = request.getUserPrincipal().getName();
        return appService.findByUser(email);
    }

}
