package com.example.projetsecurite.web;



import com.example.projetsecurite.entities.Client;
import com.example.projetsecurite.repositorie.ClientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping({"/user/index" ,"/"})
    public String ListeClient(Model model, @RequestParam(defaultValue = "") String keyword) {
        List<Client> clients = clientRepository.chercher(keyword);
        model.addAttribute("clients", clients);
        model.addAttribute("keyword", keyword);
        return "listeClients";
    }

    @GetMapping("/admin/delete")
    public String deleteClient(@RequestParam Long id) {
        clientRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/nouveauClient")
    public String ajouterClient(Model model) {
        model.addAttribute("client", new Client());
        return "nouveauClient";
    }

    @PostMapping("/admin/enregistrerClient")
    public String enregistrerClient(@Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return client.getId() != null ? "editerClient" : "nouveauClient";
        }
        clientRepository.save(client);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/edit")
    public String editer(Model model, @RequestParam Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        model.addAttribute("client", client);
        return "editerClient";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/notAutorized")
    public String notAuthorized() {
        return "notAutorized";
    }
}

