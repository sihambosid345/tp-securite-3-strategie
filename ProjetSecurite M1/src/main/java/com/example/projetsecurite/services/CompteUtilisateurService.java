package com.example.projetsecurite.services;

import com.example.projetsecurite.entities.AppRole;
import com.example.projetsecurite.entities.Utilisateur;

public interface CompteUtilisateurService {

    Utilisateur addNewUser(
            String username,
            String password,
            String confirmPassword,
            String email
    );

    AppRole addNewRole(String roleName);

    void addRoleToUser(String username, String role);

    void removeRoleFromUser(String username, String role);

    Utilisateur loadUserByUsername(String username);
}
