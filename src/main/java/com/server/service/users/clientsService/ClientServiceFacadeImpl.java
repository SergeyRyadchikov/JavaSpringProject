package com.server.service.users.clientsService;

import com.server.model.users.ApiUsers;
import com.server.model.users.Client;
import com.server.model.users.Gender;
import com.server.service.users.ApiUsersServiceImpl;
import com.server.service.users.UserServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceFacadeImpl implements UserServiceFacade <Client, ApiUsers, Integer> {

    public static ClientServiceFacadeImpl instance;

    private final ApiUsersServiceImpl apiUsersService;

    private final ClientServiceImpl clientService;


    @Autowired
    public ClientServiceFacadeImpl(ApiUsersServiceImpl apiUsersService, ClientServiceImpl clientService) {
        this.apiUsersService = apiUsersService;
        this.clientService = clientService;
    }

    public static ClientServiceFacadeImpl getInstance() {
        if (ClientServiceFacadeImpl.instance == null){
            instance = new ClientServiceFacadeImpl(new ApiUsersServiceImpl(), new ClientServiceImpl());
            return instance;
        } else {
            return instance;
        }
    }

    @Override
    public void create(Client client, ApiUsers apiUsers) {
        clientService.create(client);
        apiUsersService.create(apiUsers);
    }

    @Override
    public List<Client> readAll() {
        return clientService.readAll();
    }

    @Override
    public Client read(Integer id) {
        return clientService.read(id);
    }

    @Override
    public boolean update(Client client, Integer id) {
        return clientService.update(client, id);
    }

    @Override
    public boolean delete(Integer id) {
        return clientService.delete(id);
    }

    public List<Client> filterByGender(Gender gender){
        return clientService.filterByGender(gender);
    }

    public Client findByPhone(String phone) {
        return clientService.findByPhone(phone);
    }

}
