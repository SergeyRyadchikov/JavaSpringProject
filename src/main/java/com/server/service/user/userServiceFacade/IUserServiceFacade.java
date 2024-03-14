package com.server.service.user.userServiceFacade;

import com.server.dto.user.UserRegistrationDto;
import java.util.List;

public interface IUserServiceFacade<T, E, tId>{

    T create(UserRegistrationDto userRegistrationDto);
    List<T> readAll();

    T read(tId id);


    T update(E dto, tId id);

    boolean delete(tId id);

}
