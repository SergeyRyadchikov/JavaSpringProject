package com.server.service;

import com.server.model.ApiUsers;

public interface ApiUsersService<A, I extends Number> extends AppService<ApiUsers, Integer>{

    ApiUsers findApiUsersByPhone(String phone);
}
