package com.server.service;

import com.server.model.Client;
import com.server.model.Gender;
import com.server.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {

        Client client = new Client();
        client.setName("Name1");
        clientService.create(client);

        Mockito.verify(clientRepository, Mockito.times(1)).save(client);
    }

    @Test
    void readAll() {

        clientService.readAll();

        Mockito.verify(clientRepository, Mockito.times(1)).findAll();
    }

    @Test
    void read() {

        int id = 1;
        clientService.read(id);

        Mockito.verify(clientRepository, Mockito.times(1)).getReferenceById(id);
    }

    @Test
    void update() {

        /*
        Тест работает, но работает некорректно. В Методе сервиса есть условие if,
        не разобрался, как его правильно проверять
         */

        int id = 1;

        Client  client = new Client();
        client.setName("Name2");
        clientService.create(client);

        clientService.update(client, id);

        when(clientRepository.existsById(id)).thenReturn(true);
        verify(clientRepository, times(1)).existsById(id);
        verify(clientRepository, times(1)).save(client);
    }


    @Test
    void filterByGender() {
        List<Client> clientList = new ArrayList<>();
        Client client = new Client();
        client.setGender(Gender.FEMALE);
        client.setPhone("989846541");

        when(clientRepository.findClientByGender(Gender.valueOf("FEMALE"))).thenReturn(clientList);
        System.out.println(clientList.size());


    }

    @Test
    void findByPhone() {
        Client client = new Client();
        client.setName("Name");
        client.setPhone("989846541");

        when(clientRepository.findByPhone("989846541")).thenReturn(client);

        Client client1 = clientService.findByPhone("989846541");

        assertEquals("989846541", client1.getPhone());

        Mockito.verify(clientRepository, times(1)).findByPhone("989846541");
    }
}