package com.example.projetsecurite.repositorie;

import com.example.projetsecurite.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.nom LIKE %:kw%")
    List<Client> chercher(@Param("kw") String keyword);
}
