package com.server.controller.restControllers.user;

import com.server.dto.user.ClientDto;
import com.server.dto.user.UserRegistrationDto;
import com.server.model.user.Client;
import com.server.model.user.Gender;
import com.server.service.user.userServiceFacade.ClientServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RestClientController implements IClientController{

    private final ClientServiceFacade clientService;

    @Autowired
    public RestClientController(ClientServiceFacade clientService) {
        this.clientService = clientService;
    }


    @Override
    public ResponseEntity<?> create(UserRegistrationDto userRegistrationDto) {

        Client client = clientService.create(userRegistrationDto);

        return new ResponseEntity<>(client, HttpStatus.CREATED);

    }


    @Override
    public ResponseEntity<List<Client>> readAll() {

        final List<Client> clients = clientService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @Override
    public ResponseEntity<Client> readId(int id) {

        final Client client = clientService.read(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @Override
    public ResponseEntity<Client> update(int id, ClientDto clientDto) {

        final Client client = clientService.update(clientDto, id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }


    @Override
    public ResponseEntity<?> delete(int id) {

        final boolean deleted = clientService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }


    @Override
    public ResponseEntity<List<Client>> filterByGender(Gender gender){

        final List<Client> clients = clientService.filterByGender(gender);

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<Client> filterByPhone(String phone) {

        final Client client = clientService.findByPhone(phone);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}