package com.ashu.microservices.order.dto;

import java.math.BigDecimal;

public record OrderResponseDTO( Long id,
                               String orderNumber,
                               String skuCode,
                               BigDecimal price,
                               Integer quantity) {
}
