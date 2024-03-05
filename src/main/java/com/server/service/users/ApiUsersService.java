package com.server.service.users;

import com.server.model.users.ApiUsers;
import com.server.service.AppService;
import java.util.Optional;

public interface ApiUsersService<A, I extends Number> extends AppService<ApiUsers, Integer> {

    Optional<ApiUsers> findApiUsersByPhone(String phone);
}
