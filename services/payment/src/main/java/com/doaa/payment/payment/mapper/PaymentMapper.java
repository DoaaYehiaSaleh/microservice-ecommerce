package com.doaa.payment.payment.mapper;

import com.doaa.payment.payment.dto.PaymentRequest;
import com.doaa.payment.payment.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
