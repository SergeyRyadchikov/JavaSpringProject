package com.server.repository;

import com.server.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {

    Moderator findByPhone(String phone);
}
