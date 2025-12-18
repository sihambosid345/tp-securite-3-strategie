package com.example.projetsecurite.web;

import com.example.projetsecurite.entities.Client;
import com.example.projetsecurite.repositorie.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class ClientRestController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping({"/clients"})
    public List<Client> listeClients() {
        return clientRepository.findAll();

    }
    @DeleteMapping("/clients/list")
    public List<Client> deleteClient(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return clientRepository.findAll();
    }



    @GetMapping("/clients/{id}")
    public Client chercherParIdClient(@PathVariable Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Client ajouterClient(@RequestBody Client client) {
        Client c = new Client();
        c.setNom(client.getNom());
        c.setAge(client.getAge());

        return clientRepository.save(c);
    }
    @PutMapping("/clients/{id}")
    public Client modifierClient(@PathVariable Long id, @RequestBody Client client) {
        Client c = clientRepository.findById(id).orElseThrow();
        c.setNom(client.getNom());
        c.setAge(client.getAge());
        return clientRepository.save(c);
    }



}
