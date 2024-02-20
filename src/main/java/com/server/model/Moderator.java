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
@Schema(description = "Админ")
public class Moderator {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Идентификатор администратора")
    private int id;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min=2, max=50)
    @Schema(description = "Имя администратора")
    private String name;

    @Column(nullable = false)
    @NotBlank
    @Size(min=2, max=15)
    @Schema(description = "Телефон администратора")
    private String phone;



}
