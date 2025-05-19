package com.doaa.product.product.dto;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        double quantity,
        BigDecimal price
) {
}
