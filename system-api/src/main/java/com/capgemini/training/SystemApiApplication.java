package com.capgemini.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.capgemini.training")
public class SystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApiApplication.class, args);
    }
}