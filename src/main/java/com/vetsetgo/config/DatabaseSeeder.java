package com.vetsetgo.config;

import com.vetsetgo.repository.VetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner seedDatabase(VetRepository vetRepository) {
        return args -> {
            // TODO: Implement seeding logic to create default entities
        };
    }
}