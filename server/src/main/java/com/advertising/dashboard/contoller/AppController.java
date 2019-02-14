package com.advertising.dashboard.contoller;

import com.advertising.dashboard.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app")
public class AppController {
    @Autowired
    private AppService appService;


}
