package com.doaa.payment.payment.dto;

import com.doaa.payment.payment.model.Customer;
import com.doaa.payment.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest (
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
