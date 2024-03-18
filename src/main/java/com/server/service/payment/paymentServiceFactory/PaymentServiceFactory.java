package com.server.service.payment.paymentServiceFactory;

import com.server.entity.payment.PaymentMethod;
import com.server.repository.payment.PaymentRepository;
import com.server.service.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceFactory {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceFactory(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public IPaymentService create(PaymentMethod paymentMethod){

        if(paymentMethod == PaymentMethod.BANK_CARD){

            return new PaymentBankCardServiceCreator(paymentRepository).create();

        } else if (paymentMethod == PaymentMethod.E_WALLET) {

            return new PaymentEWalletServiceCreator(paymentRepository).create();

        } else if (paymentMethod == PaymentMethod.FPS){

            return new PaymentFPSServiceCreator(paymentRepository).create();

        } else {

            throw new RuntimeException("Передан несуществующий способ оплаты");

        }

    }

}
