package com.server.service.payment;

import com.server.entity.purchase.Purchase;

public interface IPaymentService {

    boolean pay(Purchase purchase);

}
