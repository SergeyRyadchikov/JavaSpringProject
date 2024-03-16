package com.server.entity.purchase;

import com.server.entity.product.Goods;
import com.server.entity.product.Services;
import com.server.entity.user.Client;
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
    private List<Goods> goodsList;

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

    public Purchase(List<Goods> goodsList, List<Services> servicesList, Client client) {
        this.goodsList = goodsList;
        this.servicesList = servicesList;
        this.client = client;
    }

    public Purchase(List<Goods> goodsList, Client client) {
        this.goodsList = goodsList;
        this.client = client;
        int resultAmount = 0;
        for (Goods product : goodsList) {
            resultAmount += product.getAmount();
        }
        this.purchaseAmount = resultAmount;
    }
}
