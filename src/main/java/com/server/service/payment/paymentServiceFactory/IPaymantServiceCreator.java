package com.server.service.payment.paymentServiceFactory;

import com.server.entity.payment.PaymentMethod;
import com.server.service.payment.IPaymentService;

public interface IPaymantServiceCreator {

    IPaymentService create();

}
