package com.server.service.user.apiUserService;

import com.server.entity.user.ApiUsers;
import java.util.List;


public interface IApiUsersService<T, tId, tPhone> {

    void create(T t);
    List<T> readAll();


    T readId(tId id);


    boolean update(T t, tId id);


    boolean delete(tId id);
    ApiUsers findApiUsersByPhone(tPhone phone);
}
