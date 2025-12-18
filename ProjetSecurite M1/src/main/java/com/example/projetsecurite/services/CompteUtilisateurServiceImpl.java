package com.example.projetsecurite.services;

import com.example.projetsecurite.entities.AppRole;
import com.example.projetsecurite.entities.Utilisateur;
import com.example.projetsecurite.repositorie.AppRoleRepository;
import com.example.projetsecurite.repositorie.UtilisateurRepository;
import com.example.projetsecurite.services.CompteUtilisateurService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class CompteUtilisateurServiceImpl implements CompteUtilisateurService {


    private UtilisateurRepository userRepository;
    private AppRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur addNewUser(String username, String password, String confirmPassword, String email) {
        Utilisateur user = userRepository.findByUsername(username);

        if (user != null) {
            throw new RuntimeException("User already exists");
        }

        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }

        user = Utilisateur.builder()
                .idUtilisateur(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        return userRepository.save(user);
    }

    @Override
    public AppRole addNewRole(String roleName) {
        AppRole appRole = roleRepository.findById(roleName).orElse(null);
        if (appRole != null) {
            throw new RuntimeException("Role already exists");
        }
        appRole = AppRole.builder()
                .role(roleName)
                .build();

        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        Utilisateur user = userRepository.findByUsername(username);
        AppRole appRole = roleRepository.findById(role).orElseThrow();
        user.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        Utilisateur user = userRepository.findByUsername(username);
        AppRole appRole = roleRepository.findById(role).orElseThrow();
        user.getRoles().remove(appRole);
    }

    @Override
    public Utilisateur loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
