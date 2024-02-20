package com.server.service;

import com.server.config.UserDetailsConfig;
import com.server.model.ApiUsers;
import com.server.repository.ApiUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApiUsersRepository apiUsersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApiUsers> user = apiUsersRepository.findApiUsersByPhone(username);
        return user.map(UserDetailsConfig::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
