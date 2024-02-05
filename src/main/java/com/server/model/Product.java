package com.server.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Продукт или услуга")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Идентификатор продукта или услуги")
    private int id;

    @Column(nullable = false)
    @Schema(description = "Наименование продукта или услуги")
    private String productName;

    @Column(nullable = false)
    @Schema(description = "Стоимость продукта или услуги")
    private int amount;

}
