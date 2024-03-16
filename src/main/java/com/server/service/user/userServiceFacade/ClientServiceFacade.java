package com.server.service.user.userServiceFacade;

import com.server.dto.user.ClientDto;
import com.server.dto.user.UserRegistrationDto;
import com.server.model.user.ApiUsers;
import com.server.model.user.Client;
import com.server.model.user.Gender;
import com.server.model.user.Role;
import com.server.service.user.apiUserService.ApiUsersService;
import com.server.service.user.clientsService.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceFacade implements IUserServiceFacade<Client, ClientDto, Integer> {

    private final ApiUsersService apiUsersService;

    private final ClientService clientService;


    @Autowired
    public ClientServiceFacade(ApiUsersService apiUsersService, ClientService clientService) {
        this.apiUsersService = apiUsersService;
        this.clientService = clientService;
    }


    @Override
    public Client create(UserRegistrationDto userRegistrationDto) {

        ApiUsers apiUser = new ApiUsers();
        apiUser.setPhone(userRegistrationDto.phone());
        apiUser.setRole(Role.LEAD);
        apiUser.setPassword(userRegistrationDto.password());

        apiUsersService.create(apiUser);

        Client client = new Client();
        client.setPhone(userRegistrationDto.phone());
        client.setName(userRegistrationDto.name());

        clientService.create(client);

        return client;

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
    public Client update(ClientDto clientDto, Integer id) {

        ApiUsers apiUsers = apiUsersService.findApiUsersByPhone(clientService.read(id).getPhone());

        Client client = clientService.update(clientDto, id);

        if (apiUsers != null){

            this.updateRole(client, apiUsers);

        }

        return client;

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

    private void updateRole(Client client, ApiUsers apiUsers){

        if (apiUsers.getRole() == Role.LEAD){

            if (!client.getName().isBlank() && !client.getEmail().isBlank()){

                apiUsers.setRole(Role.CLIENT);

                apiUsersService.update(apiUsers, apiUsers.getId());

            }

        }

    }

}
