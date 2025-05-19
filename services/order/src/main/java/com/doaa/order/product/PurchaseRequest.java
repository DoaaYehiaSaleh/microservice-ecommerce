package com.doaa.order.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "product id is required")
        Integer productId,
        @Positive(message = "quantity must be positive")
        double quantity

) {
}
