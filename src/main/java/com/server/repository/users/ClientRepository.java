package com.server.repository.users;


import com.server.model.users.Client;
import com.server.model.users.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findClientByGender(Gender gender);
    Client findByPhone(String phone);

}

