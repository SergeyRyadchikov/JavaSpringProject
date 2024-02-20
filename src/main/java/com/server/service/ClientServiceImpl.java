package com.server.service;

import com.server.annotation.LogException;
import com.server.annotation.LogExecution;
import com.server.model.Client;
import com.server.model.Gender;
import com.server.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @LogExecution
    @LogException
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    @LogExecution
    @LogException
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Override
    @LogExecution
    @LogException
    public Client read(int id) {
        return (Client) clientRepository.getReferenceById(id);
    }

    @Override
    @LogExecution
    @LogException
    public boolean update(Client client, int id) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            clientRepository.save(client);
            return true;
        } else {
            return false;
        }

    }

    @Override
    @LogExecution
    @LogException
    public boolean delete(int id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Клиента с " + id + " не найдено");
        }
    }

    @Override
    @LogExecution
    @LogException
    public List<Client> filterByGender(Gender gender){
        List<Client> filterClients = clientRepository.findClientByGender(gender);
        return filterClients;
    }

    @Override
    public Client findByPhone(String phone) {
        Client client = clientRepository.findByPhone(phone);
        if (client != null){
            return client;
        } else {
            return null;
        }
    }
}