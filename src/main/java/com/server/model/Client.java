package com.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Schema(description = "Клиент")
public class Client {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Идентификатор клиента")
    private int id;

    @Column(nullable = false)
    @NotBlank
    @Size(min=2, max=50)
    @Schema(description = "Имя клиента")
    private String name;

    @Column(nullable = true)
    @Schema(description = "Электронная почта клиента")
    private String email;

    @Column(nullable = true, unique = true)
    @Schema(description = "Номер телефона клиента")
    @Size(min=6, max=12)
    private String phone;


    @Schema(description = "Пол")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    public Client() {
    }
}