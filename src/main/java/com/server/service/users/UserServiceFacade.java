package com.server.service.users;

import com.server.model.users.ApiUsers;
import java.util.List;

public interface UserServiceFacade <T, E extends ApiUsers, tId>{

    void create(T user, E apiUsers);
    List<T> readAll();

    T read(tId id);


    boolean update(T t, tId id);

    boolean delete(tId id);

}
