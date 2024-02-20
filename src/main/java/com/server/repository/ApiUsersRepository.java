package com.server.repository;

import com.server.model.ApiUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApiUsersRepository  extends JpaRepository<ApiUsers, Integer> {
    Optional<ApiUsers> findApiUsersByPhone(String phone);
}
