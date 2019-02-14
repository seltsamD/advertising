package com.advertising.dashboard.contoller;

import com.advertising.dashboard.model.dto.AppDto;
import com.advertising.dashboard.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app")
public class AppController {
    @Autowired
    private AppService appService;


    @GetMapping("/all")
    public List<AppDto> findAll() {
        return appService.findAll();
    }

    @PostMapping
    public AppDto save(@RequestBody AppDto appDto) {
        return appService.save(appDto);
    }

}
