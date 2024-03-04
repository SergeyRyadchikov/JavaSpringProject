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
@Schema(description = "Товар")
public class Goods implements Product{

    public static final String PRODUCT_TYPE = "GOODS";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Идентификатор товара")
    private int id;

    @Column(nullable = false)
    @Schema(description = "Наименование товара")
    private String productName;

    @Column(nullable = false)
    @Schema(description = "Категория товара")
    @Enumerated(EnumType.STRING)
    private CategoryGoods category;

    @Column(nullable = false)
    @Schema(description = "Стоимость товара")
    private int amount;

}
