package com.example.projetsecurite;

import com.example.projetsecurite.entities.Client;
import com.example.projetsecurite.repositorie.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetSecuriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetSecuriteApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(Client.builder()
                    .nom("ALAMI")
                    .age(35)
                    .build());

            clientRepository.save(Client.builder()
                    .nom("BENNANI")
                    .age(30)
                    .build());
        };
    }
}
