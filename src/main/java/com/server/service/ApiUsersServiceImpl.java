package com.server.service;

import com.server.model.ApiUsers;
import com.server.repository.ApiUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiUsersServiceImpl implements ApiUsersService<ApiUsers, Integer> {

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
    public Optional<ApiUsers> findApiUsersByPhone(String phone) {

        Optional<ApiUsers> apiUser = apiUsersRepository.findApiUsersByPhone(phone);

        if (apiUser.isEmpty()) {
            return Optional.empty();
        } else {
            return apiUser;
        }
    }
}
