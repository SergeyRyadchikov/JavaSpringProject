package com.server.controller.templatesControllers;



import com.server.model.Client;
import com.server.service.ClientService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;


@Controller
public class ClientsController {

    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/clients")
    public String allClients(Model model) {

        final List<Client> clients = clientService.readAll();

        model.addAttribute("clients", clients);
        model.addAttribute("client", Client.class);

        return "index";
    }

    @PostMapping("/clients")
    public String addClient(@Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("animals", clientService.readAll());
            return "index";
        }
        clientService.create(client);
        return "redirect:/clients";
    }
}


