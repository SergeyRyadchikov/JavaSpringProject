package com.server.repository;

import com.server.model.ApiUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUsersRepository  extends JpaRepository<ApiUsers, Integer> {
    ApiUsers findApiUsersByPhone(String phone);
}
