package com.example.projetsecurite.Securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager manager =
                new JdbcUserDetailsManager(dataSource);

        if (!manager.userExists("user1")) {
            UserDetails user1 = User.withUsername("user1")
                    .password(passwordEncoder().encode("1234"))
                    .roles("USER")
                    .build();
            manager.createUser(user1);
        }

        if (!manager.userExists("user2")) {
            UserDetails user2 = User.withUsername("user2")
                    .password(passwordEncoder().encode("1234"))
                    .roles("USER")
                    .build();
            manager.createUser(user2);
        }

        if (!manager.userExists("admin")) {
            UserDetails admin = User.withUsername("admin")
                    .password(passwordEncoder().encode("1234"))
                    .roles("USER", "ADMIN")
                    .build();
            manager.createUser(admin);
        }

        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/login",
                                "/error",
                                "/webjars/**",
                                "/h2-console/**"
                        ).permitAll()

                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/index", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )

                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/notAutorized")
                );

        http.csrf(csrf ->
                csrf.ignoringRequestMatchers("/h2-console/**")
        );

        http.headers(headers ->
                headers.frameOptions(frame -> frame.sameOrigin())
        );

        return http.build();
    }
}
