package com.server.repository.user;

import com.server.model.user.ApiUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApiUsersRepository  extends JpaRepository<ApiUsers, Integer> {
    ApiUsers findApiUsersByPhone(String phone);
}