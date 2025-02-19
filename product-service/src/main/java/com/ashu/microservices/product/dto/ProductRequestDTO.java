package com.ashu.microservices.product.dto;

import lombok.Data;

import java.math.BigDecimal;

//@Data
//public class ProductRequestDTO {
//
//    private String name;
//    private String description;
//    private BigDecimal price;
//}

public record ProductRequestDTO(
    String name,
    String description,
    BigDecimal price){}
