package com.example.projetsecurite;

import com.example.projetsecurite.entities.Client;
import com.example.projetsecurite.repositorie.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class ProjetSecuriteApplication {

    public static void main(String[] args) { SpringApplication.run(ProjetSecuriteApplication.class, args);}

    @Bean
    CommandLineRunner start(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(Client.builder()
                    .nom("ALAMI").age(35)
                    .build());
            clientRepository.save(Client.builder()
                    .nom("BENNANI")
                    .age(35)
                    .build());
        };
    }

    @Bean
    CommandLineRunner jdbcauthentification(JdbcUserDetailsManager jdbcUserDetailsManager,
                                           PasswordEncoder passwordEncoder,
                                           ClientRepository clientRepository) {
        return args -> {

            if (!jdbcUserDetailsManager.userExists("user1")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user1")
                                .password(passwordEncoder.encode("1234"))
                                .roles("USER")
                                .build()
                );
            }

            if (!jdbcUserDetailsManager.userExists("user2")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user2")
                                .password(passwordEncoder.encode("1234"))
                                .roles("USER")
                                .build()
                );
            }

            if (!jdbcUserDetailsManager.userExists("admin")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin")
                                .password(passwordEncoder.encode("1234"))
                                .roles("ADMIN", "USER")
                                .build()
                );
            }

            clientRepository.findAll().forEach(System.out::println);
        };
    }

}
