package com.server.service;

import com.server.model.ApiUsers;
import java.util.Optional;

public interface ApiUsersService<A, I extends Number> extends AppService<ApiUsers, Integer>{

    Optional<ApiUsers> findApiUsersByPhone(String phone);
}
