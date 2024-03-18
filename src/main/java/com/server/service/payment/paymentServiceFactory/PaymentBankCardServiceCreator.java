package com.server.service.payment.paymentServiceFactory;

import com.server.repository.payment.PaymentRepository;
import com.server.service.payment.IPaymentService;
import com.server.service.payment.PaymentBankCardService;

public class PaymentBankCardServiceCreator implements IPaymantServiceCreator{

    private final PaymentRepository paymentRepository;

    public PaymentBankCardServiceCreator(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    @Override
    public IPaymentService create() {

        return new PaymentBankCardService(paymentRepository);

    }
}
