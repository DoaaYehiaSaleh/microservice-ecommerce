package com.doaa.notification.kafka.order;

import com.doaa.notification.kafka.payment.PaymentMethod;


import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
