package com.server.controller.restControllers;


import com.server.model.ApiUsers;
import com.server.model.Client;
import com.server.model.Gender;
import com.server.model.Role;
import com.server.service.ApiUsersService;
import com.server.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("v1/clients")
@Tag(
        name = "Пользователи",
        description = "Все методы для работы с пользователями системы"
)
public class RestClientController {

    private final ClientService clientService;

    private final ApiUsersService apiUsersService;

    @Autowired
    public RestClientController(ClientService clientService, ApiUsersService apiUsersService) {
        this.clientService = clientService;
        this.apiUsersService = apiUsersService;
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Client client, @RequestParam("Пароль") String password) {
        ApiUsers apiUser = new ApiUsers();
        apiUser.setPhone(client.getPhone());
        apiUser.setRole(Role.CLIENT);
        apiUser.setPassword(password);
        apiUsersService.create(apiUser);
        clientService.create(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Client>> read() {
        final List<Client> clients = clientService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) {
        final Client client = clientService.read(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) {
        final boolean updated = clientService.update(client, id);

        return updated
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = clientService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @RequestMapping(value = "/filter",method = RequestMethod.GET)
    public ResponseEntity<List<Client>> filterByGender(@RequestParam("gender")Gender gender){
        final List<Client> clients = clientService.filterByGender(gender);
        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}