package com.server.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Client {

    private int id;

    private String name;

    private String email;

    private String phone;

    public Client() {
    }
}