package com.server.service.user.clientsService;

import com.server.annotation.LogException;
import com.server.annotation.LogExecution;
import com.server.dto.user.ClientDto;
import com.server.entity.user.ApiUsers;
import com.server.entity.user.Client;
import com.server.entity.user.Gender;
import com.server.repository.user.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @LogExecution
    @LogException
    public void create(Client client) {

        clientRepository.save(client);

    }

    @LogExecution
    @LogException
    public List<Client> readAll() {

        return clientRepository.findAll();

    }

    @LogExecution
    @LogException
    public Client readId(Integer id) {

        return clientRepository.getReferenceById(id);

    }

    @LogExecution
    @LogException
    public Client update(ClientDto clientDto, Integer id) {

        if (clientRepository.existsById(id)) {

            Client client = clientRepository.getReferenceById(id);

            if (!clientDto.name().isBlank()){

                client.setName(clientDto.name());

            }

            if (!clientDto.email().isBlank()){

                client.setEmail(clientDto.email());

            }

            client.setGender(clientDto.gender());

            clientRepository.save(client);

            return client;

        } else {

            return null;

        }

    }

    @LogExecution
    @LogException
    public boolean delete(Integer id) {

        if (clientRepository.existsById(id)) {

            clientRepository.deleteById(id);

            return true;

        } else {

            throw new RuntimeException("Клиента с " + id + " не найдено");

        }
    }


    @LogExecution
    @LogException
    public List<Client> filterByGender(Gender gender){

        return clientRepository.findClientByGender(gender);

    }


    public Client findClientByApiUsers(ApiUsers apiUsers) {

        Client client = clientRepository.findClientByApiUsers(apiUsers);

        if (client != null){

            return client;

        } else {

            return null;

        }
    }
}