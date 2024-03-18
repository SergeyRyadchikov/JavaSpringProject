package com.server.service.payment.paymentServiceFactory;

import com.server.repository.payment.PaymentRepository;
import com.server.service.payment.IPaymentService;
import com.server.service.payment.PaymentFPSService;

public class PaymentFPSServiceCreator implements IPaymantServiceCreator{

    private final PaymentRepository paymentRepository;

    public PaymentFPSServiceCreator(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public IPaymentService create() {

        return new PaymentFPSService(paymentRepository);

    }
}
