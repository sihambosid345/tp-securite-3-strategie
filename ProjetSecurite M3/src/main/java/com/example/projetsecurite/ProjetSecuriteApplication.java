package com.example.projetsecurite;

import com.example.projetsecurite.entities.Client;
import com.example.projetsecurite.entities.AppUser;
import com.example.projetsecurite.repositorie.ClientRepository;
import com.example.projetsecurite.repositorie.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjetSecuriteApplication {

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ProjetSecuriteApplication.class, args);
    }

    @Bean
    CommandLineRunner initClients(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(Client.builder().nom("ALAMI").age(35).build());
            clientRepository.save(Client.builder().nom("BENNANI").age(30).build());
        };
    }

    @Bean
    CommandLineRunner initUsers(AppUserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("user1").isEmpty()) {
                userRepository.save(AppUser.builder()
                        .username("user1")
                        .password(passwordEncoder.encode("1234"))
                        .role("USER")
                        .build());
            }
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(AppUser.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("1234"))
                        .role("ADMIN")
                        .build());
            }
        };
    }
}
