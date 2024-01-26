package com.server.repository;


import com.server.model.Client;

import java.util.List;

public interface ClientRepository<T> {

    void create(Client client);

    T getId(int id);

    List<T> getAll();

    boolean update(int id, Client client);

    boolean delete(int id);

}

