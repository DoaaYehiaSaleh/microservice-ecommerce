package com.doaa.order.order.dto;

import com.doaa.order.payment.PaymentMethod;
import com.doaa.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "order amount must be positive")
        BigDecimal amount,
        @NotNull(message = "payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer id is required")
        @NotEmpty(message = "customer id is required")
        @NotBlank(message = "customer id is required")
        String customerId,
        @NotEmpty(message = "You should add at least one product")
        List<PurchaseRequest> products
) {
}
