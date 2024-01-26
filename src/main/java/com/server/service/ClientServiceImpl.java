package com.server.service;

import com.server.model.Client;
import com.server.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void create(Client client) {
        clientRepository.create(client);
    }

    @Override
    public List<Client> readAll() {
        return clientRepository.getAll();
    }

    @Override
    public Client read(int id) {
        return (Client) clientRepository.getId(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if(clientRepository.update(id, client)){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(int id) {
        if(clientRepository.delete(id)){
            return true;
        } else {
            return false;
        }
    }
}