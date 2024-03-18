package com.server.service.payment.paymentServiceFactory;

import com.server.repository.payment.PaymentRepository;
import com.server.service.payment.IPaymentService;
import com.server.service.payment.PaymentEWalletService;

public class PaymentEWalletServiceCreator implements IPaymantServiceCreator{

    private final PaymentRepository paymentRepository;

    public PaymentEWalletServiceCreator(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public IPaymentService create() {

        return new PaymentEWalletService(paymentRepository);

    }
}
