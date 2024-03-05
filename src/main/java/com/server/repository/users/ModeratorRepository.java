package com.server.repository.users;

import com.server.model.users.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {

    Moderator findByPhone(String phone);
}
