package com.server.service;

import com.server.model.ApiUsers;
import com.server.model.Moderator;
import com.server.repository.ApiUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiUsersServiceImpl implements ApiUsersService<ApiUsers, Integer> {

    @Autowired
    private ApiUsersRepository apiUsersRepository;


    @Override
    public void create(ApiUsers apiUsers) {
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

        if (apiUser != null) {
            return apiUser;
        } else {
            return null;
        }
    }
}
