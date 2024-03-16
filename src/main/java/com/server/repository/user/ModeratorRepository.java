package com.server.repository.user;

import com.server.entity.user.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {

    Moderator findByPhone(String phone);
}
