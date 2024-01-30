package com.server.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Client {

    private int id;

    @NotBlank
    @Size(min=2, max=50)
    private String name;

    private String email;

    private String phone;

    public Client() {
    }
}