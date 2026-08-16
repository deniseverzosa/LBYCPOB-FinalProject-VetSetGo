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
            // If the database is empty, create default Vets
            if (vetRepository.count() == 0) {
                Vet vet1 = new Vet("V202", "Dr. Bob Miller", "vetpass", "drbob@email.com", "555-9876", "VET-LICENSE-99");
                Vet vet2 = new Vet("V203", "Dr. Alice Smith", "vetpass", "dralice@email.com", "555-1111", "VET-LICENSE-100");
                Vet vet3 = new Vet("V204", "Dr. Charlie Brown", "vetpass", "drcharlie@email.com", "555-2222", "VET-LICENSE-101");

                vetRepository.save(vet1);
                vetRepository.save(vet2);
                vetRepository.save(vet3);

                System.out.println("Default Vets Initialized:");
                System.out.println("- ID: V202 | Dr. Bob Miller");
                System.out.println("- ID: V203 | Dr. Alice Smith");
                System.out.println("- ID: V204 | Dr. Charlie Brown");
            }
        };
    }
}