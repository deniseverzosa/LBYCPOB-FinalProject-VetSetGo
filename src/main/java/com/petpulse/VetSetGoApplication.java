package com.petpulse;

import com.petpulse.model.*;
import com.petpulse.utils.DateTimeUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class VetSetGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetSetGoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runConsoleDemo() {
        return args -> {
            System.out.println("==================================================");
            System.out.println("          PETPULSE SYSTEM INITIALIZING            ");
            System.out.println("==================================================");
