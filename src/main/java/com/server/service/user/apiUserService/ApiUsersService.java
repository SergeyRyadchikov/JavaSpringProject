package com.server.service.user.apiUserService;

import com.server.model.user.ApiUsers;
import com.server.repository.user.ApiUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiUsersService implements IApiUsersService<ApiUsers, Integer> {

    @Autowired
    private ApiUsersRepository apiUsersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void create(ApiUsers apiUsers) {

        apiUsers.setPassword(passwordEncoder.encode(apiUsers.getPassword()));

        apiUsersRepository.save(apiUsers);

    }

    @Override
    public List<ApiUsers> readAll() {

        return apiUsersRepository.findAll();

    }

    @Override
    public ApiUsers read(Integer id) {

        return (ApiUsers) apiUsersRepository.getReferenceById(id);

    }

    @Override
    public boolean update(ApiUsers apiUsers, Integer id) {

        if (apiUsersRepository.existsById(id)) {

            apiUsers.setId(id);

            apiUsersRepository.save(apiUsers);

            return true;

        } else {

            return false;

        }

    }

    @Override
    public boolean delete(Integer id) {

        if (apiUsersRepository.existsById(id)) {

            apiUsersRepository.deleteById(id);

            return true;

        } else {

            return false;

        }

    }


    @Override
    public ApiUsers findApiUsersByPhone(String phone) {

        ApiUsers apiUser = apiUsersRepository.findApiUsersByPhone(phone);

        return apiUser != null ?  apiUser : null;
        
    }
}