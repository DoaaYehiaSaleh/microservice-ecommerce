package com.doaa.notification.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}
