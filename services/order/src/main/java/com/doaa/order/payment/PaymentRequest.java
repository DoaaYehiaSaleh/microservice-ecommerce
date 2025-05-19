package com.doaa.order.payment;

import com.doaa.order.customer.CustomerResponse;
import com.doaa.order.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
