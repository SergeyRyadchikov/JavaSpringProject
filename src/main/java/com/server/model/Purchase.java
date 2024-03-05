package com.server.model;

import com.server.model.products.Goods;
import com.server.model.products.Services;
import com.server.model.users.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Заказ")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Идентификатор заказа")
    private int id;

    @Column(unique = false)
    @ManyToMany
    @Schema(description = "Список товаров")
    private List<Goods> productList;

    @Column(unique = false)
    @ManyToMany
    @Schema(description = "Список услуг")
    private List<Services> servicesList;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    @Schema(description = "Клиент")
    private Client client;


    @Column(nullable = true)
    @Schema(description = "Сумма заказа")
    private int purchaseAmount;

    public Purchase(List<Goods> productList, Client client) {
        this.productList = productList;
        this.client = client;
        int resultAmount = 0;
        for (Goods product: productList) {
            resultAmount += product.getAmount();
        }
        this.purchaseAmount = resultAmount;
    }
}
