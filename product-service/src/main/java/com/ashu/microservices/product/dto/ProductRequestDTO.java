package com.ashu.microservices.product.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String name,
        String description,
        BigDecimal price) {
}
