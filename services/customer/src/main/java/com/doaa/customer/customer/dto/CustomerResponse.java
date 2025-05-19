package com.doaa.customer.customer.dto;

import com.doaa.customer.customer.model.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {

}