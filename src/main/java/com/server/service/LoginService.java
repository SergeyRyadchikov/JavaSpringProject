package com.server.service;


import com.server.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private ApiUsersService apiUsersService;

    @Autowired
    public LoginService(ApiUsersService apiUsersService) {
        this.apiUsersService = apiUsersService;
    }

    public Optional<Integer> login(String phone, String password) {
        String userPassword = apiUsersService.findApiUsersByPhone(phone).getPassword();

        if (userPassword.equals(password)){
            return Optional.of(1);
        }

        return Optional.empty();
    }
}
