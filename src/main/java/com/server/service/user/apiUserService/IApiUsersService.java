package com.server.service.user.apiUserService;

import com.server.model.user.ApiUsers;
import com.server.service.AppService;
import java.util.Optional;

public interface IApiUsersService<A, I extends Number> extends AppService<ApiUsers, Integer> {

    ApiUsers findApiUsersByPhone(String phone);
}
