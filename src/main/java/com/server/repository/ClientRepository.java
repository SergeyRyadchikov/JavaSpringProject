package com.server.repository;


import com.server.model.Client;
import com.server.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findClientByGender(Gender gender);

}

