package com.server.controller.restControllers.users;

import com.server.dto.users.ClientDTO;
import com.server.dto.users.ClientRegDTO;
import com.server.model.users.ApiUsers;
import com.server.model.users.Client;
import com.server.model.users.Gender;
import com.server.model.users.Role;
import com.server.service.users.clientsService.ClientServiceFacadeImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("v1")
@Tag(
        name = "Пользователи",
        description = "Все методы для работы с пользователями системы"
)
public class RestClientController {

    private final ClientServiceFacadeImpl clientService;

    public RestClientController() {
        this.clientService = ClientServiceFacadeImpl.getInstance();
    }

    @RequestMapping(value = "/new-client",method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ClientRegDTO clientRegDTO) {

        Client client = new Client();
        client.setPhone(clientRegDTO.phone());
        client.setName(clientRegDTO.name());

        ApiUsers apiUser = new ApiUsers();
        apiUser.setPhone(client.getPhone());
        apiUser.setRole(Role.CLIENT);
        apiUser.setPassword(clientRegDTO.password());

        clientService.create(client, apiUser);

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/clients",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<List<Client>> read() {
        final List<Client> clients = clientService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) {
        final Client client = clientService.read(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody ClientDTO clientDTO) {

        Client client = new Client();
        client.setName(clientDTO.name());
        client.setPhone(clientDTO.phone());
        client.setEmail(clientDTO.email());
        client.setGender(clientDTO.gender());

        final boolean updated = clientService.update(client, id);

        return updated
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = clientService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @RequestMapping(value = "clients/filter",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<List<Client>> filterByGender(@RequestParam("gender")Gender gender){
        final List<Client> clients = clientService.filterByGender(gender);
        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}