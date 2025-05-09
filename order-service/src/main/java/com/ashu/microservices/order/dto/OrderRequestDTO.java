package com.ashu.microservices.order.dto;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public record OrderRequestDTO( String skuCode,
        BigDecimal price,
        Integer quantity,
        UserDetails userDetails) {

    public record UserDetails(String email, String firstName, String lastName) {
    }
}
