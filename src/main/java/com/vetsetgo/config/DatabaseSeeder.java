package com.vetsetgo.config;

import com.vetsetgo.model.Vet;
import com.vetsetgo.repository.VetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner seedDatabase(VetRepository vetRepository) {
        return args -> {
            // If the database is empty, create a default Vet
            if (vetRepository.count() == 0) {
                Vet vet = new Vet("V202", "Dr. Bob Miller", "vetpass", "drbob@email.com", "555-9876", "VET-LICENSE-99");
                vetRepository.save(vet);
                System.out.println("Default Vet Initialized - ID: V202 | Password: vetpass");
            }
        };
    }
}