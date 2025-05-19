package com.doaa.order.order.dto;

import com.doaa.order.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
    Integer orderId,
    String reference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    String customerId
) {
}
