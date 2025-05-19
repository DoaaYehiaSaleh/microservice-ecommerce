package com.doaa.order.kafka;

import com.doaa.order.customer.CustomerResponse;
import com.doaa.order.payment.PaymentMethod;
import com.doaa.order.product.PurchaseResponse;


import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
