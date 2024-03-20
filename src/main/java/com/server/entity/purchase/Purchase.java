package com.server.entity.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Schema(description = "Заказ")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Идентификатор заказа")
    private int id;

    @Column(unique = false)
    @ManyToMany
    @Schema(description = "Список товаров")
    // Добавить валидацию длины списка min = 1
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


    @Column(nullable = false)
    @Schema(description = "Статус заказа")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;


    /**
     * Конструктор для рассчета стоимости заказа с товарами и услугами
     * @param goodsList список товаров
     * @param servicesList список услуг
     * @param client пользователь, совершающий заказ
     */
    public Purchase(List<Goods> goodsList, List<Services> servicesList, Client client) {
        this.goodsList = goodsList;
        this.servicesList = servicesList;
        this.client = client;
        this.status = OrderStatus.CREATED;
        int resultAmount = 0;
        for (Services services : servicesList) {
            resultAmount += services.getAmount();
        }
        for (Goods product : goodsList) {
            resultAmount += product.getAmount();
        }
        this.purchaseAmount = resultAmount;
    }


    /**
     * Конструктор для создания заказа с товарами, но без услуг
     * @param goodsList список товаров
     * @param client пользователь, совершающий заказ
     */
    public Purchase(List<Goods> goodsList, Client client) {
        this.goodsList = goodsList;
        this.client = client;
        this.status = OrderStatus.CREATED;
        int resultAmount = 0;
        for (Goods product : goodsList) {
            resultAmount += product.getAmount();
        }
        this.purchaseAmount = resultAmount;
    }
}
