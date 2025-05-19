package com.doaa.order.customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
