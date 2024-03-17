package com.server.repository.purchase;


import com.server.entity.purchase.OrderStatus;
import com.server.entity.purchase.Purchase;
import com.server.entity.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    List<Purchase> findAllByClient(Client client);

    List<Purchase> findAllByStatus(OrderStatus orderStatus);

}
