package com.server.repository;

import com.server.model.Client;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service
public class ClientRepositoryImpl implements ClientRepository <Client>{

    private final List<Client> listOfAllClients = new LinkedList<>();
    private static int counter = 1;
    public ClientRepositoryImpl() {
    }

    @Override
    public void create(Client client) {


        Client newClient = new Client();
        newClient.setId(counter++);
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
        newClient.setPhone(client.getPhone());

        listOfAllClients.add(newClient);

    }

    @Override
    public Client getId(int id) {
        Client client = listOfAllClients.stream().filter(it -> it.getId() == id).findFirst().orElse(null);
        if (client != null) {
            return client;
        } else {
            System.out.printf("Клиент с " + id + " не найден");
            return null;
        }
    }

    @Override
    public List<Client> getAll() {
        return listOfAllClients;
    }

    @Override
    public boolean update(int id, Client client) {

        Client upClient = listOfAllClients.stream().filter(it -> it.getId() == id).findFirst().orElse(null);
        if (upClient != null) {
            upClient.setName(client.getName());
            upClient.setEmail(client.getEmail());
            upClient.setPhone(client.getPhone());
            return true;
        } else {
            System.out.printf("Клиент с " + id + " не найден");
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        Client client = listOfAllClients.stream().filter(it -> it.getId() == id).findFirst().orElse(null);
        if (client != null) {
            listOfAllClients.remove(client);
            return true;
        } else {
            System.out.printf("Клиент с " + id + " не найден");
            return false;
        }
    }
}
