package com.example.projetsecurite.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    private String idUtilisateur;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;


}
