package com.example.projetsecurite.repositorie;

import com.example.projetsecurite.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByUsername(String username);

    Utilisateur save(Utilisateur user);
}
