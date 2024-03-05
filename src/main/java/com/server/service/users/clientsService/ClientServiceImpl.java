package com.server.service.users.clientsService;

import com.server.annotation.LogException;
import com.server.annotation.LogExecution;
import com.server.model.users.Client;
import com.server.model.users.Gender;
import com.server.repository.users.ClientRepository;
import com.server.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClientServiceImpl implements AppService<Client, Integer> {

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
    public Client read(Integer id) {
        return (Client) clientRepository.getReferenceById(id);
    }

    @Override
    @LogExecution
    @LogException
    public boolean update(Client client, Integer id) {
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
        return filterClients;
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