package com.example.projetsecurite.repositorie;

import com.example.projetsecurite.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

    Optional<AppRole> findById(String role);

}
