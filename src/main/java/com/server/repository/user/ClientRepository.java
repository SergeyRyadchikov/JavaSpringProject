package com.server.repository.user;


import com.server.entity.user.Client;
import com.server.entity.user.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findClientByGender(Gender gender);
    Client findByPhone(String phone);

}

