package com.advertising.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AdDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdDashboardApplication.class, args);
    }

}

