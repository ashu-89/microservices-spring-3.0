package com.ashu.microservices.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

//@Data
//@AllArgsConstructor
//public class ProductResponseDTO {
//
//    private String id;
//    private String name;
//    private String description;
//    private BigDecimal price;
//}

public record ProductResponseDTO(String id,
        String name,
        String description,
        BigDecimal price){

}
