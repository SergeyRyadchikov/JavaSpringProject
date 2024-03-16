package com.server.service.user.clientsService;

import com.server.annotation.LogException;
import com.server.annotation.LogExecution;
import com.server.dto.user.ClientDto;
import com.server.model.user.Client;
import com.server.model.user.Gender;
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
    public Client read(Integer id) {

        return (Client) clientRepository.getReferenceById(id);

    }

    @LogExecution
    @LogException
    public Client update(ClientDto clientDto, Integer id) {

        if (clientRepository.existsById(id)) {

            Client client = clientRepository.getReferenceById(id);

            if (!clientDto.name().isBlank()){

                client.setName(clientDto.name());

            }

            if (!clientDto.phone().isBlank()) {

                client.setPhone(clientDto.phone());

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

        List<Client> filterClients = clientRepository.findClientByGender(gender);

        if (!filterClients.isEmpty() || filterClients != null){

            return filterClients;

        } else {

            return null;

        }

    }


    public Client findByPhone(String phone) {

        Client client = clientRepository.findByPhone(phone);

        if (client != null){

            return client;

        } else {

            return null;

        }
    }
}