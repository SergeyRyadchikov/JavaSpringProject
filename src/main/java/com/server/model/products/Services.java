package com.server.model.products;


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
@Schema(description = "Услуга")
public class Services implements Product{

    public static final String PRODUCT_TYPE = "SERVICES";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Идентификатор услуги")
    private int id;

    @Column(nullable = false)
    @Schema(description = "Наименование услуги")
    private String productName;

    @Column(nullable = false)
    @Schema(description = "Стоимость услуги")
    private int amount;

}
